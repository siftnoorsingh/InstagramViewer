package com.project.gagan.instagram_gagan;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


/**
 * Created by Gagan on 30-Sep-15.
 */
public class SignUpOrLogin extends AppCompatActivity {


    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Sign Up","Tiny Pic","Login"};
    int numOfTabs =3;

    /*
    The following two variables for Login Fragment
     */
    private EditText usernameView;
    private EditText passwordView;

    /*
    The following two variables for Sign up Fragment
     */
    private EditText usernameField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private EditText emailField;
    private EditText nameField;
    private int minPasswordLength;
    private static final String USER_OBJECT_NAME_FIELD = "name";

    private static final int MIN_PASSWORD_LENGTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,numOfTabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);


        // set a centre tab (Tiny Pic) on load
        pager.setCurrentItem(1);



        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {






            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {



                if (position==pager.getAdapter().getCount()-3) {
                    // Set up the sign up form
                    signUpPage();

                }

                if (position == pager.getAdapter().getCount() - 1) {

                    // Set up the login form.
                    usernameView = (EditText) findViewById(R.id.username);
                    passwordView = (EditText) findViewById(R.id.password);

                    // Set up the submit button click handler
                    findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            // Validate the log in data
                            boolean validationError = false;
                            StringBuilder validationErrorMessage =
                                    new StringBuilder(getResources().getString(R.string.error_intro));
                            if (isEmpty(usernameView)) {
                                validationError = true;
                                validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
                            }
                            if (isEmpty(passwordView)) {
                                if (validationError) {
                                    validationErrorMessage.append(getResources().getString(R.string.error_join));
                                }
                                validationError = true;
                                validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
                            }
                            validationErrorMessage.append(getResources().getString(R.string.error_end));

                            // If there is a validation error, display the error
                            if (validationError) {
                                Toast.makeText(SignUpOrLogin.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                                        .show();
                                return;
                            }

                            // Set up a progress dialog
                            final ProgressDialog dlg = new ProgressDialog(SignUpOrLogin.this);
                            dlg.setTitle("Please wait.");
                            dlg.setMessage("Logging in.  Please wait.");
                            dlg.show();
                            // Call the Parse login method
                            ParseUser.logInInBackground(usernameView.getText().toString(), passwordView.getText()
                                    .toString(), new LogInCallback() {

                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    dlg.dismiss();
                                    if (e != null) {
                                        // Show the error message
                                        Toast.makeText(SignUpOrLogin.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                    } else {
                                        // Start an intent for the session activity
                                        Intent intent = new Intent(SignUpOrLogin.this, SessionActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    });
                }
            }

            private boolean isEmpty(EditText etText) {
                if (etText.getText().toString().trim().length() > 0) {
                    return false;
                } else {
                    return true;
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

                    // Assiging the Sliding Tab Layout View
                    tabs = (SlidingTabLayout) findViewById(R.id.tabs);
                    tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

                    // Setting Custom Color for the Scroll bar indicator of the Tab View
                    tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                        @Override
                        public int getIndicatorColor(int position) {
                            return getResources().getColor(R.color.tabsScrollColor);
                        }
                    });

                    // Setting the ViewPager For the SlidingTabsLayout
                    tabs.setViewPager(pager);
                    }


    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.

                    navigateToHome();
                } else {
                    // Login failed!
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpOrLogin.this);
                    builder.setMessage(e.getMessage())
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void navigateToHome() {
        // Let's go to the MainActivity
        Intent intent = new Intent(SignUpOrLogin.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void signUpPage(){
        minPasswordLength = MIN_PASSWORD_LENGTH;
        usernameField = (EditText) findViewById(R.id.signup_username_input);
        passwordField = (EditText) findViewById(R.id.signup_password_input);
        confirmPasswordField = (EditText) findViewById(R.id.signup_confirm_password_input);
        emailField = (EditText) findViewById(R.id.signup_email_input);
        nameField = (EditText) findViewById(R.id.signup_name_input);

        findViewById(R.id.create_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));

                final String username = usernameField.getText().toString().trim();
                final String password = passwordField.getText().toString().trim();
                String confirmPassword = confirmPasswordField.getText().toString().trim();
                String email = emailField.getText().toString().trim();

                String name = null;
                if (nameField != null) {
                    name = nameField.getText().toString();
                }

                if (username.length() == 0) {

                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_username));

                }  if (password.length() == 0) {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
                }  if (password.length() < minPasswordLength) {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_password_length));
                }  if (confirmPassword.length() == 0) {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_confirm_password));
                }  if (!password.equals(confirmPassword)) {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_password_donot_match));
                    confirmPasswordField.selectAll();
                    confirmPasswordField.requestFocus();
                }

                validationErrorMessage.append(getResources().getString(R.string.error_end));

                if (validationError) {

                    // If there is a validation error, display the error
                    Toast.makeText(SignUpOrLogin.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(SignUpOrLogin.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Creating Account.  Please wait.");
                dlg.show();

                final ParseUser user = new ParseUser();

                // Set standard fields
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                // Set additional custom fields only if the user filled it out
                if (name.length() != 0) {
                    user.put(USER_OBJECT_NAME_FIELD, name);
                }

                // First query to check whether a ParseUser with
                // the given phone number already exists or not
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", username);

                query.findInBackground(new FindCallback<ParseUser>() {
                                           @Override
                                           public void done(List<ParseUser> parseUsers, ParseException e) {
                                               dlg.dismiss();

                                               if (e == null) {
                                                   // User already exists ? then login
                                                   if (parseUsers.size() > 0) {
                                                       loginUser(username, password);
                                                   } else {
                                                       // No user found, so signup
                                                       signupUser(user);
                                                   }
                                               }

                                           }
                                       }

                );


            }

        });
    }

    private void signupUser(ParseUser user) {
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Signup successful!

                    navigateToHome();
                } else {
                    // Fail!
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpOrLogin.this);
                    builder.setMessage(e.getMessage())
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
                }
