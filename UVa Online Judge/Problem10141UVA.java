import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem10141 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int numRfp = 1;
        StringBuilder output = new StringBuilder();
        String data;

        while (!(data = in.readLine()).equals("0 0")) {

            String[] dataSplit = data.split(" ");

            String bestProposalName = null;
            int maxRequirementsMet = Integer.MIN_VALUE;
            double lowestPrice = Double.MAX_VALUE;

            int numRequirements = Integer.parseInt(dataSplit[0]);
            int numProposals = Integer.parseInt(dataSplit[1]);

            while (numRequirements-- > 0) {
                in.readLine();
            }

            while (numProposals-- > 0) {
                String proposalName = in.readLine();
                String[] proposalData = in.readLine().split(" ");

                double price = Double.parseDouble(proposalData[0]);
                int numProposalRequirements = Integer.parseInt(proposalData[1]);
                int requirementsMet = 0;

                while ((numProposalRequirements-- > 0)) {
                    in.readLine();
                    requirementsMet++;
                }

                block:
                {
                    if (requirementsMet >= maxRequirementsMet) {
                        if (requirementsMet == maxRequirementsMet && price >= lowestPrice) {
                            break block;
                        }

                        maxRequirementsMet = requirementsMet;
                        lowestPrice = price;
                        bestProposalName = proposalName;
                    }
                }
            }

            output.append("RFP #").append(numRfp++).append("\n").append(bestProposalName).append("\n\n");
        }

        System.out.println(output.delete(output.length() - 2, output.length()));
    }
}