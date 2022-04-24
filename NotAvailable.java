class NotAvailable extends Exception
{
    public NotAvailable() {
        
    }
    @Override
    public String toString()
    {
        return "Not Available !";
    }
}