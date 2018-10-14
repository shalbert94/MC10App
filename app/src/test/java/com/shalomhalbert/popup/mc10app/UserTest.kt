package com.shalomhalbert.popup.mc10app

import com.google.common.truth.Truth.assertThat
import com.shalomhalbert.popup.mc10app.models.User
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * [Parameterized] test for [User]
 */
@RunWith(Parameterized::class)
class UserTest(val id: String,
               val accountId: String,
               val email: String,
               val firstName: String,
               val lastName: String,
               val locale: String,
               val timezone: String,
               val legaleseVersionRequired: Int?,
               val legaleseVersionAccepted: Int?) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>> =
                listOf(
                        arrayOf(
                                "16aaf5b0-443f-11e6-ab9d-0a624d7022db",
                                "168656b0-443f-11e6-96fc-0a2e9e9dd655",
                                "mobile_test@mc10inc.com", "Mobile", "Test", "en_us",
                                "America/New_York", 1, 1
                        )
                )
    }

    @Test
    fun `Properties are same as input`() {
        //Given arguments for initializing a User

        //When the object is initialized
        val user = User(id, accountId, email, firstName, lastName, locale, timezone,
                legaleseVersionRequired, legaleseVersionAccepted)

        //Then the properties should keep their initial values
        assertThat(user.id).isSameAs(id)
        assertThat(user.accountId).isSameAs(accountId)
        assertThat(user.email).isSameAs(email)
        assertThat(user.firstName).isSameAs(firstName)
        assertThat(user.lastName).isSameAs(lastName)
        assertThat(user.locale).isSameAs(locale)
        assertThat(user.timezone).isSameAs(timezone)
        assertThat(user.legaleseVersionRequired).isEqualTo(legaleseVersionRequired)
        assertThat(user.legaleseVersionAccepted).isEqualTo(legaleseVersionAccepted)
    }

    @Test
    fun `Properties are not equal to wrong value`() {
        //Given arguments for initializing a User

        //When the object is initialized
        val user = User(id, accountId, email, firstName, lastName, locale, timezone,
                legaleseVersionRequired, legaleseVersionAccepted)

        //Then the properties are not equal to a value other than their initial value
        assertThat(user.id).isNotSameAs("")
        assertThat(user.accountId).isNotSameAs("")
        assertThat(user.email).isNotSameAs("")
        assertThat(user.firstName).isNotSameAs("")
        assertThat(user.lastName).isNotSameAs("")
        assertThat(user.locale).isNotSameAs("")
        assertThat(user.timezone).isNotSameAs("")
        assertThat(user.legaleseVersionRequired).isNotEqualTo(0)
        assertThat(user.legaleseVersionAccepted).isNotEqualTo(0)
    }

}