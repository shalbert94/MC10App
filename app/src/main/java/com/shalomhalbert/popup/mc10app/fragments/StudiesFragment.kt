package com.shalomhalbert.popup.mc10app.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.shalomhalbert.popup.mc10app.R
import com.shalomhalbert.popup.mc10app.adapters.StudiesAdapter
import com.shalomhalbert.popup.mc10app.extensions.SharedPrefExts
import com.shalomhalbert.popup.mc10app.extensions.updateSharedPreferences
import com.shalomhalbert.popup.mc10app.models.Study
import com.shalomhalbert.popup.mc10app.viewmodels.StudiesViewModel
import okhttp3.ResponseBody
import android.bluetooth.BluetoothAdapter
import android.support.v7.app.AlertDialog
import com.shalomhalbert.popup.mc10app.extensions.fetchGetStudiesData
import com.shalomhalbert.popup.mc10app.extensions.fetchTimezone


/**
 * A [Fragment] for displaying a list of the user's studies
 */
class StudiesFragment : Fragment() {

    companion object {
        val TAG = StudiesFragment::class.java.simpleName
        fun newInstance() = StudiesFragment()
    }

    lateinit var viewModel: StudiesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_studies, container, false)
        viewModel = ViewModelProviders.of(this).get(StudiesViewModel::class.java)
        setHasOptionsMenu(true)

        val recyclerView = view.findViewById<RecyclerView>(R.id.studiesRecyclerView)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = StudiesAdapter(SharedPrefExts.fetchTimezone(activity))
        }

        viewModel.getStudiesData = SharedPrefExts.fetchGetStudiesData(activity)

        subscribeToObservers(recyclerView)

        when(bluetoothEnabled()) {
            true -> viewModel.fetchStudies()
            false -> showBluetoothAlert(view)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.studies, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logoutMenuOption -> viewModel.logout()
        }
        return true
    }

    private fun subscribeToObservers(recyclerView: RecyclerView) {
        //Once studies are retrieved, update the RecyclerView
        viewModel.studies.observe(this, Observer<Array<Study>> {
            val adapter = recyclerView.adapter as StudiesAdapter
            adapter.addStudies(it)
        })

        //When logged out, delete user's data and return them to LoginFragment
        viewModel.loggedOut.observe(this, Observer<ResponseBody> {
            val empty = ""
            updateSharedPreferences(getString(R.string.access_token), empty)
            updateSharedPreferences(getString(R.string.access_token_expiration), 0L) //Indicates a new access token is required
            updateSharedPreferences(getString(R.string.user_id), empty)
            updateSharedPreferences(getString(R.string.user_account_id), empty)
            updateSharedPreferences(getString(R.string.user_timezone), empty)

            openLoginFrag()
        })
    }

    /** Store a string in Shared Preferences */
    private fun updateSharedPreferences(key: String, value: Any) {
        SharedPrefExts.updateSharedPreferences(activity, key, value)
    }

    /** Checks if the user has Bluetooth enabled*/
    private fun bluetoothEnabled(): Boolean {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        return bluetoothAdapter != null && bluetoothAdapter.isEnabled
    }

    /** Shows an [AlertDialog] to user, which logs them out when positive button is clicked*/
    private fun showBluetoothAlert(view: View) {
        val alert = AlertDialog.Builder(view.context)
        alert.apply {
            setTitle(R.string.bluetooth_not_enabled_title)
            setMessage(R.string.bluetooth_not_enabled_message)
            setPositiveButton(android.R.string.yes) { _, _ ->
                viewModel.logout()
            }
            show()
        }
    }

    /** Replaces this fragment with LoginFragment */
    private fun openLoginFrag() {
        activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment.newInstance(), LoginFragment.TAG)
                .commit()
    }

}
