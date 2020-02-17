class ConstructorJava {

    // long result = isValid ? 1 : 0; // if this line is here, it does not compile
    boolean isValid = true;
    long result = isValid ? 1 : 0;

    public ConstructorJava() {
        isValid = false;
    }

    public static void main(String[] args) {
        ConstructorJava o = new ConstructorJava();
        System.out.println("ConstructorJava class:" + o.result);
    }
}

