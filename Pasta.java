public class Pasta extends Food {
    public Pasta() { super(); }

    public void setPrice() {
        super.price=quantity*60;
    }
}
