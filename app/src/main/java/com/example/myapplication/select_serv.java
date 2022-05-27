package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class select_serv extends AppCompatActivity {



    private FirebaseAuth mAuth;


    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String ADDRESS = "ADDRESS";
    public static final String PASSWORD = "PASSWORD";


    private String vendorName, vendorEmail, vendorNumber, vendorAddress, vendorPassword, vendorConfirmPas, v_service_type;



    private TextView nameView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_serv);

        Intent intent = getIntent();



        mAuth = FirebaseAuth.getInstance();
        vendorName = intent.getStringExtra(NAME);
        vendorEmail = intent.getStringExtra(EMAIL);
        vendorNumber = intent.getStringExtra(PHONE);
        vendorAddress = intent.getStringExtra(ADDRESS);
        vendorPassword= intent.getStringExtra(PASSWORD);

        nameView = (TextView) findViewById(R.id.print_name);






        Button sett = (Button) findViewById(R.id.settings);
        Button v_apl1 = (Button) findViewById(R.id.v_apl_log);
        Button v_apl2 = (Button) findViewById(R.id.v_apl_tx);
        Button v_elec1 = (Button) findViewById(R.id.v_elec_log);
        Button v_elec2 = (Button) findViewById(R.id.v_elec_tx);
        Button v_plb1 = (Button) findViewById(R.id.v_plb_log);
        Button v_plb2 = (Button) findViewById(R.id.v_plb_tx);
        Button v_hcl1 = (Button) findViewById(R.id.v_hcl_log);
        Button v_hcl2 = (Button) findViewById(R.id.v_hcl_tx);
        Button v_ttr1 = (Button) findViewById(R.id.v_ttr_log);
        Button v_ttr2 = (Button) findViewById(R.id.v_ttr_tx);
        Button v_pam1 = (Button) findViewById(R.id.v_pam_log);
        Button v_pam2 = (Button) findViewById(R.id.v_pam_tx);
        Button v_cpr1 = (Button) findViewById(R.id.v_cpr_log);
        Button v_cpr2 = (Button) findViewById(R.id.v_cpr_tx);
        Button v_hrp1 = (Button) findViewById(R.id.v_hrp_log);
        Button v_hrp2 = (Button) findViewById(R.id.v_hrp_tx);

        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(select_serv.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        v_apl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Appliances";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();

            }

        });

        v_apl2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Appliances";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_elec1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Electrical";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_elec2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Electrical";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_plb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Plumbing";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_plb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Plumbing";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_hcl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Home Cleaning";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_hcl2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Home Cleaning";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_ttr1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Tutoring";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_ttr2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Tutoring";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_pam1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Packaging and Moving";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_pam2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Packaging and Moving";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_cpr1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Computer Repair";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_cpr2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Computer Repair";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_hrp1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Home Repair and Painting";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

        v_hrp2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v_service_type = "Home Repair and Painting";
                AlertDialog.Builder altdial = new AlertDialog.Builder(select_serv.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        addVendor();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }

        });

    }

    private void addVendor() {


        mAuth.createUserWithEmailAndPassword(vendorEmail, vendorPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Vendor vendor = new Vendor(vendorName, vendorEmail, vendorNumber, vendorAddress, vendorPassword, v_service_type, "Vendor", 0, 0, 0, 0, 0,  0.0);

                            //within paranthesis returns id for registered user
                            FirebaseDatabase.getInstance().getReference("Vendor")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(vendor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(select_serv.this, "User has been registered succesfully", Toast.LENGTH_LONG).show();

                                        //redirect to Login layout
                                        Intent intent = new Intent(select_serv.this, Vend_login.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.from_right,R.anim.to_left);

                                    }
                                    else
                                    {
                                        Toast.makeText(select_serv.this, "Failed to register. Try again.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(select_serv.this, "Failed to register. Try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }






}