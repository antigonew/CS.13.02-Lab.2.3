public class Number {
    private int denary;

    public Number(int denary) {
        this.denary = denary;
    }

    public int getDenary() {
        return denary;
    }

    public void setDenary(int denary) {
        this.denary = denary;
    }

    public String getSignedBinary() {
        StringBuilder binary = new StringBuilder();
        int num = Math.abs(denary);

        if (num == 0) {
            binary.append("0");
        } else {
            while (num > 0) {
                binary.insert(0, num % 2);
                num /= 2;
            }
        }
        binary.insert(0, '0');


        // If the number is negative, convert to two's complement form
        if (denary < 0) {
            int numBits = binary.length();

            // Invert all the bits
            for (int i = 0; i < numBits; i++) {
                char bit = binary.charAt(i);
                if (bit == '0') {
                    binary.setCharAt(i, '1');
                } else {
                    binary.setCharAt(i, '0');
                }
            }

            // Add 1 to the result
            int carry = 1;
            for (int i = numBits - 1; i >= 0; i--) {
                char bit = binary.charAt(i);
                if (bit == '1') {
                    if (carry == 1) {
                        binary.setCharAt(i, '0');
                    } else {
                        break;
                    }
                } else {
                    if (carry == 1) {
                        binary.setCharAt(i, '1');
                        break;
                    }
                }
            }
        }

        return binary.toString();
    }

    public String getHexadecimal() {

        /*
        StringBuilder hexadecimal = new StringBuilder();
        int num = Math.abs(denary);

        if (num == 0) {
            hexadecimal.append("0");
        } else {
            while (num > 0) {
                int remainder = num % 16;
                hexadecimal.insert(0, getHexDigit(remainder));
                num /= 16;
            }
        }
        hexadecimal.insert(0, "0");

        // convert 2s complement
        if (denary < 0) {
            // Invert all the digits
            for (int i = 0; i < hexadecimal.length(); i++) {
                char digit = hexadecimal.charAt(i);
                if (Character.isDigit(digit)) {
                    int value = 15 - (digit - '0');
                    hexadecimal.setCharAt(i, getHexDigit(value));
                } else {
                    int value = 15 - (digit - 'A' + 10);
                    hexadecimal.setCharAt(i, getHexDigit(value));
                }
            }

            // Add 1 to the result
            int carry = 1;
            for (int i = hexadecimal.length() - 1; i >= 0; i--) {
                char digit = hexadecimal.charAt(i);
                int value;
                if (Character.isDigit(digit)) {
                    value = (digit - '0') + carry;
                } else {
                    value = (digit - 'A' + 10) + carry;
                }

                if (value >= 16) {
                    value -= 16;
                    carry = 1;
                } else {
                    carry = 0;
                }

                hexadecimal.setCharAt(i, getHexDigit(value));
            }

        }

        System.out.println(hexadecimal);


        if(hexadecimal.charAt(0) == '0' || hexadecimal.charAt(0) == 'F') {
            hexadecimal.deleteCharAt(0);
        } else {
            hexadecimal.insert(0, "1");
        }

        return hexadecimal.toString();

         */

        String binary = getSignedBinary();

        // Convert binary to decimal
        int decimal = 0;
        int power = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            int digit = binary.charAt(i) - '0';
            decimal += digit * Math.pow(2, power);
            power++;
        }

        // Convert decimal to hexadecimal
        StringBuilder hexValue = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % 16;
            char hexDigit = (remainder < 10) ? (char) ('0' + remainder) : (char) ('A' + remainder - 10);
            hexValue.insert(0, hexDigit);
            decimal /= 16;
        }

        return hexValue.toString();


    }

    private char getHexDigit(int value) {
        if (value >= 0 && value <= 9) {
            return (char) ('0' + value);
        } else {
            return (char) ('A' + (value - 10));
        }
    }

    public String toString() {
        String binary = getSignedBinary();
        String hexadecimal = getHexadecimal();
        return "Number{denary=" + denary + " binary=" + binary + " hexadecimal=" + hexadecimal + "}";
    }

    public void negate() {
        denary = -denary;
    }
}