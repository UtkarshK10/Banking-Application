package co.mandeep_singh.bankingapp;

public class Transaction {
    int _id, _customer_id;
    double _amount;


    public Transaction() {
    }

    public Transaction(int _id, int _customer_id, double _amount) {
        this._id = _id;
        this._customer_id = _customer_id;
        this._amount = _amount;
    }
    public Transaction(int _customer_id, double _amount) {

        this._customer_id = _customer_id;
        this._amount = _amount;
    }


    public int get_customer_id() {
        return _customer_id;
    }

    public void set_customer_id(int _customer_id) {
        this._customer_id = _customer_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double get_amount() {
        return _amount;
    }

    public void set_amount(double _amount) {
        this._amount = _amount;
    }
}
