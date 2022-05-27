package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Serv_req extends AppCompatActivity {

    final MediaPlayer[] mp = new MediaPlayer[1];
    private String service_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_req);

        Intent intent = getIntent();

        Button sett = (Button) findViewById(R.id.settings);
        Button apl1 = (Button) findViewById(R.id.apl_log);
        Button apl2 = (Button) findViewById(R.id.apl_tx);
        Button elec1 = (Button) findViewById(R.id.elec_log);
        Button elec2 = (Button) findViewById(R.id.elec_tx);
        Button plb1 = (Button) findViewById(R.id.plb_log);
        Button plb2 = (Button) findViewById(R.id.plb_tx);
        Button hcl1 = (Button) findViewById(R.id.hcl_log);
        Button hcl2 = (Button) findViewById(R.id.hcl_tx);
        Button ttr1 = (Button) findViewById(R.id.ttr_log);
        Button ttr2 = (Button) findViewById(R.id.ttr_tx);
        Button pam1 = (Button) findViewById(R.id.pam_log);
        Button pam2 = (Button) findViewById(R.id.pam_tx);
        Button cpr1 = (Button) findViewById(R.id.cpr_log);
        Button cpr2 = (Button) findViewById(R.id.cpr_tx);
        Button hrp1 = (Button) findViewById(R.id.hrp_log);
        Button hrp2 = (Button) findViewById(R.id.hrp_tx);
        BottomNavigationView bottomNav = findViewById(R.id.nav_bar);

        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back = (Button) findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, Cust_main_menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });

        apl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Appliances";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        apl2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Appliances";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        elec1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Electrical";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        elec2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Electrical";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });


        plb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Plumbing";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        plb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Plumbing";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        hcl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Home Cleaning";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        hcl2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Home Cleaning";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        ttr1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Tutoring";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        ttr2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Tutoring";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        pam1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Packaging and Moving";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        pam2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Packaging and Moving";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        cpr1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Computer Repair";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        cpr2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Computer Repair";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        hrp1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Home Repair and Painting";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        hrp2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service_type = "Home Repair and Painting";
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Serv_req.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(Serv_req.this, date_time.class);
                intent.putExtra(date_time.DATE_S_TYPE, service_type);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    Intent intent = new Intent(Serv_req.this, Cust_main_menu.class);
                    startActivity(intent);
                    break;
                case R.id.nav_dash:
                    Intent nat = new Intent(Serv_req.this, Settings.class);
                    startActivity(nat);
                    break;
                case R.id.nav_info:
                    Intent ban = new Intent(Serv_req.this, INFO_class.class);
                    startActivity(ban);
                    break;
            }

            return true;
        }
    };
}