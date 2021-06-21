package co.mandeep_singh.bankingapp;

public class Customer {

    int _id;
    String _name;
    String _email;
    double _balance;
    public Customer(){   }

    public Customer(int _id, String _name, String _email, double _balance) {
        this._id = _id;
        this._name = _name;
        this._email = _email;
        this._balance = _balance;
    }
    public Customer( String _name, String _email, double _balance) {
        this._name = _name;
        this._email = _email;
        this._balance = _balance;
    }

    public double get_balance() {
        return _balance;
    }

    public int get_id() {
        return _id;
    }

    public String get_email() {
        return _email;
    }

    public String get_name() {
        return _name;
    }

    public void set_balance(double _balance) {
        this._balance = _balance;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}