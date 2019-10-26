public class NeuralNetworkLab {
    public static void main(String[] args) {
        String trainingDataFile = "src/cropPriceTraining.arff";
        String predictDataFile = "src/cropPricePredict.arff";
        String modelDataFile = "src/cropPriceModel.model";

        NeuralNetwork nn = new NeuralNetwork(trainingDataFile, predictDataFile, modelDataFile);
//        nn.train();

//        nn.predict();

         trainingDataFile = "src/creditRisk_Clean_NoCreditHistory_testing.arff";
         predictDataFile = "src/creditRisk_Clean_NoCreditHistory_predict.arff";
         modelDataFile = "src/creditRisk.model";

         nn = new NeuralNetwork(trainingDataFile, predictDataFile, modelDataFile);
//         nn.train();
        nn.predict();


    }
}
