package com.example.nutritionapp.Authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nutritionapp.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import androidx.databinding.DataBindingUtil
import com.example.nutritionapp.IngredientList.IngredientListActivity
import com.example.nutritionapp.databinding.ActivityAuthenticationLayoutBinding
import com.example.nutritionapp.wrapEspressoIdlingResource

/**
 * This class should be the starting point of the app, It asks the users to sign in / register, and redirects the
 * signed in users to the RemindersActivity.
 */
/*
 SHA-1 certificate fingerprint:
    3A:5F:39:66:46:9D:A2:75:4F:CB:7C:9D:77:28:89:18:21:EC:37:15

    Alternatively, follow the directions here:
    https://developers.google.com/maps/documentation/android/start#get-key

    Once you have your key (it starts with "AIza"), replace the "google_maps_key"
    string in this file.
    username:gilong314@gmail.com
    password:abcd1234
 */

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private  lateinit var binding : ActivityAuthenticationLayoutBinding
    //"registerForActivityResult" is a launcher that is returned by registering a callback. It is a bit backward in that first we register the callback and
    //then we receive the launcher that will actually launch the intent and whose result will be monitored by the callback. This is to ensure that the callback
    //is always registered since monitoring of the callback is decoupled from the activity to save activity memory.
    //source: https://knowledge.udacity.com/questions/650170#650189
    private lateinit var registerForActivityResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Interestingly, if we perform a setContentView(R.layout.activity_authentication) and a
        //"binding = ActivityAuthenticationBinding.inflate(getLayoutInflater())", Android will take set the layout as
        //the one specified in setContentView while ignoring the latter. This would then ignore all calls to the binding object
        //such as onSetClickListener{}. For inflating fragments, do "DataBindingUtil.inflate(inflater, R.layout,container, false)"

        //DataBindingUtil - Utility class to create ViewDataBinding from layouts.
        //setContentView - Set the Activity's content view to the given layout and return the associated binding.
        //You need DataBindingUtil since we are using ViewDataBinding and DataBindingUtil is a "Utility class to create
        // ViewDataBinding from layouts."

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_authentication_layout)
        //Alternatively, you can do:
        /* binding = com.udacity.project4.databinding.ActivityReminderDescriptionBinding.inflate(layoutInflater)
         val view : View = binding.root
         setContentView(view)*/
        if (intent.hasExtra("flag") && intent.getBooleanExtra("flag", false)) {
            wrapEspressoIdlingResource {
                AuthUI.getInstance().signOut(this)
                intent.removeExtra("flag")
            }
        }

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.button.setOnClickListener {

            //Tip: Don't start activity from here, do it from onActivityResult
            wrapEspressoIdlingResource {
                launchSignInFlow()
            }
        }

        //register
        registerForSignInResult()


        //inflating xml layout in activity
        //for inflating xml layout in fragment: DataBindingUtil.inflate(inflater, R.layout.activity_authentication, container, false)
        //binding = ActivityAuthenticationBinding.inflate(getLayoutInflater())

        binding.lifecycleOwner = this
//         TODO: Implement the create account and sign in using FirebaseUI, use sign in using email and sign in using Google

//          TODO: If the user was authenticated, send him to RemindersActivity

//          TODO: a bonus is to customize the sign in flow to look nice using :
        //https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#custom-layout
    }


    private fun launchSignInFlow() {
        wrapEspressoIdlingResource {
            // Give users the option to sign in / register with their email
            // If users choose to register with their email,
            // they will need to create a password as well
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
            )

            // Create and launch sign-in intent.
            // We listen to the response of this activity with the
            // SIGN_IN_RESULT_CODE code

            //Firebase creates an intent to a seperate activity to have the user sign in
            registerForActivityResult.launch(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build())
        }
    }

    /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         wrapEspressoIdlingResource {
             super.onActivityResult(requestCode, resultCode, data)
             if (requestCode == SIGN_IN_RESULT_CODE) {
                 val response = IdpResponse.fromResultIntent(data)
                 if (resultCode == Activity.RESULT_OK) {
                     // Successfully signed in user.
                     Log.i(
                         TAG,
                         "Successfully signed in user " +
                                 "${FirebaseAuth.getInstance().currentUser?.displayName}!"
                     )
                     // I wouldn't use navController here since we are dealing with activities and not fragments
                     val remindersIntent = Intent(this, RemindersActivity::class.java)
                     startActivity(remindersIntent)
                 } else {
                     // Sign in failed. If response is null the user canceled the sign-in flow using
                     // the back button. Otherwise check response.getError().getErrorCode() and handle
                     // the error.
                     Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
                 }
             }
         }

     }*/

    companion object {
        const val TAG = "MainFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }
    /**
     * Register a launcher used to start the process of executing an ActivityResultContract
     * so that we can listen to the result of the sign - in process
     * This must be done in onCreate() or on Attach, ie before the fragment is created
     */
    private fun registerForSignInResult() {
        registerForActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
            val response = IdpResponse.fromResultIntent(result.data)
            // Listen to the result of the sign - in process
            if(result.resultCode == Activity.RESULT_OK){
                Toast.makeText(this,"${FirebaseAuth.getInstance().currentUser?.displayName} has signed in",Toast.LENGTH_SHORT).show()
                //Log.i(TAG, "User ${FirebaseAuth.getInstance().currentUser?.displayName} has signed in")
                val intent = Intent(this,IngredientListActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this,"Sign in unsuccessful ${response?.error?.errorCode}",Toast.LENGTH_SHORT).show()
                //Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }
}
