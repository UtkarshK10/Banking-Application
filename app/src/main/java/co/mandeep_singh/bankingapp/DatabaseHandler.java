package co.mandeep_singh.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bank";
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String TABLE_TRANSACTIONS = "transactions";
    private static final String CUSTOMER_ID = "id";
    private static final String CUSTOMER_NAME = "name";
    private static final String CUSTOMER_EMAIL = "email";
    private static final String CUSTOMER_BALANCE = "balance";
    private static final String TRANSACTION_ID = "id";
    private static final String TRANSACTION_AMOUNT = "amount";
    private static final String TRANSACTION_CUSTOMER = "transaction_customer";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + CUSTOMER_ID + " INTEGER PRIMARY KEY," + CUSTOMER_NAME + " TEXT,"
                + CUSTOMER_EMAIL + " TEXT," + CUSTOMER_BALANCE + " DOUBLE" +")";
        db.execSQL(CREATE_CUSTOMERS_TABLE);

        String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
                + TRANSACTION_ID + " INTEGER PRIMARY KEY," + TRANSACTION_AMOUNT + " DOUBLE," + TRANSACTION_CUSTOMER + " INTEGER"
                + " )";
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        // Create tables again
        onCreate(db);
    }



    // code to add the new contact
    void addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CUSTOMER_NAME, customer.get_name()); // Contact Name
        values.put(CUSTOMER_EMAIL, customer.get_email()); // Contact Phone
        values.put(CUSTOMER_BALANCE, customer.get_balance()); // Contact Phone
        // Inserting Row
        db.insert(TABLE_CUSTOMERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(transaction.get_customer_id() + "======================");
        System.out.println(transaction.get_amount() + "+++++++++++++++++++++++++++++");
        ContentValues values = new ContentValues();
        values.put(TRANSACTION_AMOUNT, transaction.get_amount());
        values.put(TRANSACTION_CUSTOMER, transaction.get_customer_id());
        // Inserting Row
        db.insert(TABLE_TRANSACTIONS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
//    Contact getContact(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
//                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        // return contact
//        return contact;
//    }



    // code to get all contacts in a list view
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<Customer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.set_id(Integer.parseInt(cursor.getString(0)));
                customer.set_name(cursor.getString(1));
                customer.set_email(cursor.getString(2));
                customer.set_balance(Double.parseDouble(cursor.getString(3)));
                // Adding contact to list
                customerList.add(customer);
            } while (cursor.moveToNext());
        }

        // return contact list
        return customerList;
    }

    // code to update the single contact
    public int updateCustomer(Customer customer, double transferAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        double newBalance = customer.get_balance();
        System.out.println(newBalance+"=============================");
        newBalance+=transferAmount;
        System.out.println(newBalance+"=============================");
        ContentValues values = new ContentValues();
        values.put(CUSTOMER_BALANCE, newBalance);

        // updating row
        return db.update(TABLE_CUSTOMERS, values, CUSTOMER_ID + " = ?",
                new String[] { String.valueOf(customer.get_id()) });
    }

//    // Deleting single contact
//    public void deleteContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                new String[] { String.valueOf(contact.getID()) });
//        db.close();
//    }

    // Getting contacts Count
//    public int getContactsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }

}
