public class Request {
    private String from;
    private String to;
    private double amount;

    public Request(String from, String to, double amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }
    public static Request fromString(String content)
    {
        String[] fields = content.split("\n");
        Request result = new Request(fields[0], fields[1], Double.parseDouble(fields[2]));
        return result;
    }
    @Override
    public String toString() {
        return
                from + "\n" +
                to + "\n" +
                        amount;
    }
}
