package net.adhikary.mrtbuddy

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.github.aakira.napier.Napier
import net.adhikary.mrtbuddy.database.getDatabase

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()

        // TODO will be removed once code structure and dependancy injection is intruduced

        val dao = getDatabase(applicationContext).getDao()
        Napier.d("App Running.....")

        setContent {
            App(
                dao = dao,
                prefs = remember {
                    createDataStore(applicationContext)
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val localContext = LocalContext.current
    val dao = getDatabase(localContext).getDao()

    App(
        dao = dao,
        prefs = createDataStore(context = localContext)
    )
}

