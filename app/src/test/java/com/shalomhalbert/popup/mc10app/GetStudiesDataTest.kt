package com.shalomhalbert.popup.mc10app

import com.google.common.truth.Truth.assertThat
import com.shalomhalbert.popup.mc10app.models.GetStudiesData
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * [Parameterized] test for [Studies]
 */
@RunWith(Parameterized::class)
class GetStudiesDataTest(val accountId: String,
                         val userId: String,
                         val accessToken: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<String>> =
                listOf(
                        arrayOf(
                                "16aaf5b0-443f-11e6-ab9d-0a624d7022db",
                                "168656b0-443f-11e6-96fc-0a2e9e9dd655",
                                "4zoSa8h2GBI1aWCY"
                        )
                )
    }

    @Test
    fun `Properties are same as input`() {
        //Given arguments for initializing a GetStudiesData object

        //When the object is initialized
        val getStudiesData = GetStudiesData(accountId, userId, accessToken)

        //Then the properties should keep their initial values
        assertThat(getStudiesData.accountId).isSameAs(accountId)
        assertThat(getStudiesData.userId).isSameAs(userId)
        assertThat(getStudiesData.accessToken).isSameAs(accessToken)
    }

    @Test
    fun `Properties are not equal to wrong value`() {
        //Given arguments for initializing a GetStudiesData object

        //When the object is initialized
        val getStudiesData = GetStudiesData(accountId, userId, accessToken)

        //Then the properties are not equal to a value other than their initial value
        assertThat(getStudiesData.accountId).isNotSameAs("")
        assertThat(getStudiesData.userId).isNotSameAs("")
        assertThat(getStudiesData.accessToken).isNotSameAs("")
    }

}