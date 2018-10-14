package com.shalomhalbert.popup.mc10app

import com.google.common.truth.Truth.assertThat
import com.shalomhalbert.popup.mc10app.models.Authentication
import com.shalomhalbert.popup.mc10app.models.User
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * [Parameterized] test for [Authentication]
 */
@RunWith(Parameterized::class)
class AuthenticationTest(val user: User,
                         val accessToken: String,
                         val expiration: Long) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>> =
                listOf(
                        arrayOf(
                                User("16aaf5b0-443f-11e6-ab9d-0a624d7022db",
                                        "168656b0-443f-11e6-96fc-0a2e9e9dd655",
                                        "mobile_test@mc10inc.com", "Mobile", "Test", "en_us",
                                        "America/New_York", 1, 1),
                                "4zoSa8h2GBI1aWCY",
                                1468067965856
                        )
                )
    }

    @Test
    fun `Properties are same as input`() {
        //Given arguments for initializing an Authentication

        //When the object is initialized
        val authentication = Authentication(user, accessToken, expiration)

        //Then the properties should keep their initial values
        val user = User("16aaf5b0-443f-11e6-ab9d-0a624d7022db",
                "168656b0-443f-11e6-96fc-0a2e9e9dd655",
                "mobile_test@mc10inc.com", "Mobile", "Test", "en_us",
                "America/New_York", 1, 1)

        assertThat(authentication.user).isEqualTo(user)
        assertThat(authentication.accessToken).isSameAs(accessToken)
        assertThat(authentication.expiration).isEqualTo(expiration)
    }

    @Test
    fun `Properties are not equal to wrong value`() {
        //Given arguments for initializing a Authentication

        //When the object is initialized
        val authentication = Authentication(user, accessToken, expiration)

        //Then the properties are not equal to a value other than their initial value
        val user = User("",
                "",
                "", "", "", "",
                "", 0, 0)

        assertThat(authentication.user).isNotEqualTo(user)
        assertThat(authentication.accessToken).isNotSameAs("")
        assertThat(authentication.expiration).isNotEqualTo(0)
    }
}