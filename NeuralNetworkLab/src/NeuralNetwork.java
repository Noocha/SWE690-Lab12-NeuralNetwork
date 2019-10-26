
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class NeuralNetwork {
    String training_data_set_filename;
    String predict_data_set_filename;
    String model_filename;

    public NeuralNetwork(String training_data_set_filename, String predict_data_set_filename, String model_filename) {
        this.training_data_set_filename = training_data_set_filename;
        this.predict_data_set_filename = predict_data_set_filename;
        this.model_filename = model_filename;
    }



    public Instances getDataSet(String filename) {
        ArffLoader loader = new ArffLoader();
        try {
            loader.setFile(new File(filename));
            Instances dataSet = loader.getDataSet();
            return dataSet;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void train() {
        Instances trainingData = getDataSet(training_data_set_filename);
        trainingData.setClassIndex(trainingData.numAttributes() - 1);

        MultilayerPerceptron mlp = new MultilayerPerceptron();
        mlp.setLearningRate(0.97);
        mlp.setMomentum(0.2);
        mlp.setTrainingTime(10000);
        mlp.setHiddenLayers("46");
        mlp.setValidationThreshold(80);

        try {
            mlp.buildClassifier(trainingData);
            Evaluation eval = new Evaluation(trainingData);
            eval.evaluateModel(mlp, trainingData);
            System.out.println(eval.errorRate());
            System.out.println(eval.toSummaryString());

            SerializationHelper.write(model_filename, mlp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void predict() {
         try {
             MultilayerPerceptron mlp = (MultilayerPerceptron)SerializationHelper.read(model_filename);
             Instances predictData = getDataSet(predict_data_set_filename);

             predictData.setClassIndex(predictData.numAttributes() - 1);
             double classLabel;

             for (int i = 0; i < predictData.numInstances(); i++) {
                 classLabel = mlp.classifyInstance(predictData.instance(i));
                 System.out.print(classLabel);
//                 System.out.println(", Price will be " + (classLabel + 25));

                 System.out.println(" Loan status is " + (classLabel == 0 ? "YES" : "NO"));
             }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public Instances getDataSetFromDB() {
//        String connectionURL =
//    }
}
