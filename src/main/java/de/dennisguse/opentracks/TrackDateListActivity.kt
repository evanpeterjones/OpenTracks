package de.dennisguse.opentracks

//import androidx.compose.material3.Text
// OS

// UI

// Local Data

import android.app.SearchManager
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.dennisguse.opentracks.data.ContentProviderUtils
import de.dennisguse.opentracks.ui.TrackListAdapter
import de.dennisguse.opentracks.util.PermissionRequester


/**
 * A fragment representing a list of Items.
 */
class TrackDateListActivity : ComponentActivity() {

    private lateinit var cursor: Cursor
    private lateinit var searchQuery: String
    private var columnCount = 1

    private val adapter: TrackListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestRequiredPermissions()

        setContent {
            TrackItem("YO!")
        }
    }

    private fun requestRequiredPermissions() {
        PermissionRequester.ALL.requestPermissionsIfNeeded(this, this, null) { requester: PermissionRequester? -> Toast.makeText(this, "Yikes", Toast.LENGTH_LONG).show() }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        searchQuery = ""

        if (Intent.ACTION_SEARCH == intent.action) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY).toString()
        }
    }

    override fun onResume() {
        super.onResume()

        loadData()
    }

    fun loadData() {
        cursor = ContentProviderUtils(this).searchTracks(null); // TODO: Missing Reference Query
    }
}

@Composable
fun TrackItem(testText: String) {
    Row(
            //verticalAlignment = GridLayout.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
    ) {
        //Image(bitmap = Null, contentDescription = "Artist image")
        Column {
            Card() {
                Text(text = testText)
                //Text(text = "zxcv")
            }
        }
    }
}

@Preview(name = "TrackItem")
@Composable
fun TrackTest() {
    TrackItem(testText = "test")
}