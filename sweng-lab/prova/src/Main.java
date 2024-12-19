public class Main {
    public static void main(String[] args) {
        Numero n = new Numero(10);
        System.out.println("Valore: " + n.getValue());
        System.out.println("Squared value: " + n.getSquare());
    }

    public static class Numero {
        Integer value;

        public Numero(Integer n) {
            this.value = n;
        }

        public Integer getValue() {
            return this.value;
        }

        public Integer getSquare() {
            return this.value*this.value;
        }
    }
}