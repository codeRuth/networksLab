class CRC {
    private String gX, messageText;

    private CRC(String messageText, String gX) {
        this.messageText = messageText;
        this.gX = gX;
    }

    private void computeCRC() {
        String temp;
        int start, k, bits = 0;

        for (int i = 0; i < gX.length() - 1; i++)
            messageText = messageText + 0;

        start = findStart(messageText);
        temp = messageText.substring(start, gX.length());
        start = start + gX.length();
        while (start != messageText.length() - 1) {
            if (temp.length() != gX.length()) {
                bits = gX.length() - temp.length();
                temp = addBits(temp, start, bits);
                start += bits;
            }
            temp = calculateXOR(temp, gX);
            k = findStart(temp);
            if (k == -1)
                temp = "";
            else
                temp = temp.substring(k);
        }
        System.out.println(temp);
    }

    private String addBits(String src, int start, int bits) {
        src += messageText.substring(start, start + bits);
        return src;
    }

    private int findStart(String inp) {
        return inp.indexOf("1");
    }

    private String calculateXOR(String src, String dest) {
        String temp = "";
        for (int i = 0; i < src.length(); i++)
            temp += ((int) src.charAt(i) ^ (int) dest.charAt(i));
        return temp;
    }

    public static void main(String[] args) {
        CRC c = new CRC("10110101110", "1101");
        c.computeCRC();
    }
}
