package com.shalomhalbert.popup.mc10app.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.shalomhalbert.popup.mc10app.R
import com.shalomhalbert.popup.mc10app.extensions.SharedPrefExts
import com.shalomhalbert.popup.mc10app.extensions.updateSharedPreferences
import com.shalomhalbert.popup.mc10app.models.Authentication
import com.shalomhalbert.popup.mc10app.viewmodels.LoginViewModel


/**
 * Fragment where a user logs into MC10
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    companion object {
        //Used when committing this Fragment
        val TAG = LoginFragment::class.java.simpleName
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        if (hasAccessToken()) {
            openStudiesFrag()
        }

        val usernameEditText = view.findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        /*Added these because it'll save us all time during testing.
          In production, I know not to place a id or accessToken here.*/
        usernameEditText.setText("amartin+mobiletest@mc10inc.com")
        passwordEditText.setText("yf9C79%AY9")

        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            viewModel.authenticateUser(username, password)
        }

        authenticationSubscription()

        return view
    }

    private fun authenticationSubscription() {
        val authObserver = Observer<Authentication> { t ->
                if (t != null) {
                    //Store reused values in shared preferences
                    updateSharedPreferences(getString(R.string.access_token), t.accessToken)
                    updateSharedPreferences(getString(R.string.access_token_expiration), t.expiration)
                    updateSharedPreferences(getString(R.string.user_id), t.user.id)
                    updateSharedPreferences(getString(R.string.user_account_id), t.user.accountId)
                    updateSharedPreferences(getString(R.string.user_timezone), t.user.timezone)

                    //Given the proper credentials, we're ready to show a list of the user's studies
                    openStudiesFrag()
                } else {
                    Log.e(TAG, "Posted value is null")
                }
        }
        viewModel.authLiveData.observe(this, authObserver)
    }

    /* Store a string in Shared Preferences */
    private fun updateSharedPreferences(key: String, value: Any) {
        SharedPrefExts.updateSharedPreferences(activity, key, value)
    }
    /* Checks if a valid access token is already stored*/
    private fun hasAccessToken(): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
        val expiration = sharedPref.getLong(getString(R.string.access_token_expiration), 0)
        val currentTime = System.currentTimeMillis()

        //Since expiration is stored as UNIX time, time zone doesn't need to be checked
        return expiration > currentTime
    }

    /* Replaces this fragment with StudiesFragment */
    private fun openStudiesFrag() {
        activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, StudiesFragment.newInstance(), StudiesFragment.TAG)
                .commit()
    }


}
