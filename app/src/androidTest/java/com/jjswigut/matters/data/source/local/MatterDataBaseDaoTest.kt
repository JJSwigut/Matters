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

        val matter = Matter("title", "content")
        database.matterDataBaseDao.insert(matter)


        val matterList = database.matterDataBaseDao.getAllMatters()

        assertEquals(matterList.getOrAwaitValue().first(), matter)
    }

    @Test
    fun updateMatterCheckUpdate() = runBlockingTest {

        val matterList = database.matterDataBaseDao.getAllMatters()

        var originalMatter = Matter("Title", "Content")
        database.matterDataBaseDao.insert(originalMatter)

        assertEquals(matterList.getOrAwaitValue()[0].matterTitle, "Title")

        originalMatter.matterContent = "newContent"
        originalMatter.matterTitle = "newTitle"
        database.matterDataBaseDao.update(originalMatter)


        assertEquals(1, matterList.getOrAwaitValue().size)
        assertEquals(matterList.getOrAwaitValue()[0].matterId, originalMatter.matterId)
        assertEquals(matterList.getOrAwaitValue()[0].matterTitle, "newTitle")


    }

}