package com.jjswigut.matters.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.database.MatterDatabase
import com.jjswigut.matters.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MatterDataBaseDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: MatterDatabase

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            MatterDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertMatterObserveLiveData() = runBlockingTest {
        // GIVEN - insert a task
        val matter = Matter("title", "description")
        database.matterDataBaseDao.insert(matter)

        // WHEN - Get the task by id from the database
        val matterList = database.matterDataBaseDao.getAllMatters()

        assertEquals(matterList.getOrAwaitValue(), matter)
    }

    @Test
    fun updateMatterCheckUpdate() = runBlockingTest {

        val originalMatter = Matter("Title", "Content")
        database.matterDataBaseDao.insert(originalMatter)

        val updatedMatter = Matter("new title", "new content", originalMatter.matterId)
        database.matterDataBaseDao.update(updatedMatter)

        assertThat(updatedMatter, notNullValue())
        assertThat(updatedMatter.matterId, `is`(originalMatter.matterId))
        assertThat(updatedMatter.matterTitle, `is`("new title"))
        assertThat(updatedMatter.matterContent, `is`("new content"))
    }

}