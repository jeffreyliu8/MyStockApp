package com.example.mystockapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mystockapp.repository.data.WebApi
import com.example.mystockapp.repository.impl.WebRepositoryImpl
import com.example.mystockapp.usecase.GetStocksFromWeb
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mystockapp", appContext.packageName)
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var webApi: WebApi

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun test_get_stocks_happy_path() {
        runBlocking {
            val getStocksFromWeb = GetStocksFromWeb(WebRepositoryImpl(webApi))
            val result = getStocksFromWeb()
            assert(result.isSuccess)
            if (result.isSuccess) {
                assert(result.getOrNull()!!.isNotEmpty())
            }
        }
    }
}