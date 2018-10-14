package com.shalomhalbert.popup.mc10app

import com.google.common.truth.Truth.assertThat
import com.shalomhalbert.popup.mc10app.models.Study
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * [Parameterized] test for [Study]
 */

@RunWith(Parameterized::class)
class StudyTest(val id: String,
                val accountId: String,
                val displayName: String,
                val flags: List<Any>,
                val tags: List<Any>,
                val subjects: List<Any>,
                val activities: List<Any>,
                val diaries: List<Any>,
                val deviceConfigs: List<Any>,
                val pipelines: List<Any>,
                val programs: List<Any>,
                val title: String,
                val isArchived: Boolean,
                val createdTs: Long,
                val lifecycleState: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>> =
                listOf(
                        arrayOf(
                                "16a0e390-443f-11e6-96fc-0a2e9e9dd655", "168656b0-443f-11e6-96fc-0a2e9e9dd655",
                                "Sample Study", listOf<Any>(), listOf<Any>(), listOf<Any>(),
                                listOf<Any>(), listOf<Any>(), listOf<Any>(), listOf<Any>(),
                                listOf<Any>(),
                                "This is the title for Sample Study. Lorem ipsum dolor sit amet, " +
                                        "consectetur adipiscing elit. Duis nec est quis metus " +
                                        "ultrices dictum id ut sem. Praesent felis neque, tincidunt " +
                                        "vitae ante eget, accumsan tristique neque.",
                                false, 1467894843465L, "INITIALIZED"
                        )
                )
    }

    @Test
    fun `Properties are same as input`() {
        //Given arguments for initializing a Study object

        //When the object is initialized
        val study = Study(id, accountId, displayName, flags, tags, subjects, activities, diaries,
                deviceConfigs, pipelines, programs, title, isArchived, createdTs, lifecycleState)

        //Then the properties should keep their initial values
        assertThat(study.id).isSameAs(id)
        assertThat(study.accountId).isSameAs(accountId)
        assertThat(study.displayName).isSameAs(displayName)
        assertThat(study.flags).isSameAs(flags)
        assertThat(study.tags).isSameAs(subjects)
        assertThat(study.activities).isSameAs(activities)
        assertThat(study.diaries).isSameAs(diaries)
        assertThat(study.deviceConfigs).isSameAs(deviceConfigs)
        assertThat(study.pipelines).isSameAs(pipelines)
        assertThat(study.programs).isSameAs(programs)
        assertThat(study.title).isSameAs(title)
        assertThat(study.isArchived).isSameAs(isArchived)
        assertThat(study.createdTs).isEqualTo(createdTs)
        assertThat(study.lifecycleState).isSameAs(lifecycleState)
    }

    @Test
    fun `Properties are not equal to wrong value`() {
        //Given arguments for initializing a Study object

        //When the object is initialized
        val study = Study(id, accountId, displayName, flags, tags, subjects, activities, diaries,
                deviceConfigs, pipelines, programs, title, isArchived, createdTs, lifecycleState)

        //Then the properties are not equal to a value other than their initial value
        assertThat(study.id).isNotSameAs("")
        assertThat(study.accountId).isNotSameAs("")
        assertThat(study.displayName).isNotSameAs("")
        assertThat(study.flags).isNotSameAs("")
        assertThat(study.tags).isNotSameAs("")
        assertThat(study.activities).isNotSameAs("")
        assertThat(study.diaries).isNotSameAs("")
        assertThat(study.deviceConfigs).isNotSameAs("")
        assertThat(study.pipelines).isNotSameAs("")
        assertThat(study.programs).isNotSameAs("")
        assertThat(study.title).isNotSameAs("")
        assertThat(study.isArchived).isNotSameAs("")
        assertThat(study.createdTs).isNotEqualTo(100L)
        assertThat(study.lifecycleState).isNotSameAs("")
    }

}