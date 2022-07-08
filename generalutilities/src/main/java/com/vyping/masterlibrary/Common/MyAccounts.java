package com.vyping.masterlibrary.Common;

import static com.vyping.masterlibrary.Common.MyPermissions.PERMISSION_ACCOUNTS_GET;
import static com.vyping.masterlibrary.Common.MyPermissions.PERMISSION_CODE_ACCOUNTS_GET;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class MyAccounts {


    public MyAccounts(Context context, int arrayParameters, String accountType, Interfase interfase) {

        String[] permissions = {PERMISSION_ACCOUNTS_GET};

        MyPermissions myPermissions = new MyPermissions(context, arrayParameters, permissions, PERMISSION_CODE_ACCOUNTS_GET);
        myPermissions.RequestPermissions(new MyPermissions.Interfase() {

            @Override
            public void PermissionsResult(int result) {

                AccountManager am = AccountManager.get(context);
                Account[] accounts = am.getAccounts();

                for (Account ac : accounts) {

                    String actype = ac.type;

                    if(accountType.equals(actype)) {

                        interfase.Account(ac.name);
                    }
                }
            }

            private void DummyVoid() {}
        });
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Account(String accountName);
    }
}
