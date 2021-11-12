package similarity;


public class Equations {


    /**
     * Method that calculates the cosine similarity between two vectors
     * @return the similarity between two vectors
     */
    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    /**
     * Method that calculates the Euclidean Distance between two vectors
     * @return The Euclidean Distance between two vectors
     */
    public static double euclideanDistance(double[] vectorA, double[] vectorB) {
        double sum = 0;
        for (int i = 0; i < vectorA.length; i++) {
            double d = vectorA[i] - vectorB[i];
            sum += d * d;
        }
        return Math.sqrt(sum);
    }

}
