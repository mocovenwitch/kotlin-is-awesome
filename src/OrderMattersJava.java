class OrderMattersJava {

    // long result = isValid ? 1 : 0; // if this line is here, it does not compile
    boolean isValid = true;
    long result = isValid ? 1 : 0;

    public static void main(String[] args) {
        OrderMattersJava o = new OrderMattersJava();
        System.out.println("Java class:" + o.result);
    }
}

