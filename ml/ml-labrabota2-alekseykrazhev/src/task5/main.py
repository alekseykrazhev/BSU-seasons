import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.neighbors import KNeighborsClassifier
from sklearn.datasets import load_wine
from sklearn.model_selection import train_test_split
from sklearn import metrics


class KNearestNeighbors:
    def __init__(self, n_neighbors):
        self.ty = None
        self.tX = None
        self.n_neighbors = n_neighbors

    def fit(self, X, y):
        self.tX = X
        self.ty = y

    def predict(self, X):
        # count of iterations (points)
        num_training = X.shape[0]
        # initialize array of predicted values
        prediction = np.zeros(num_training, dtype=self.ty.dtype)

        for i in range(num_training):
            # count distances between train points and test points
            distances = np.sqrt(np.sum(np.square(self.tX - X[i, :]), axis=1))
            # create array to store both distance and 'class'
            distance_label = np.column_stack((distances, self.ty))
            # sort by distances
            sorted_distance = distance_label[distance_label[:, 0].argsort()]
            # take k sorted distances
            k_sorted_distance = sorted_distance[:self.n_neighbors, :]
            # count occurences of each class
            (labels, occurence) = np.unique(k_sorted_distance[:, 1], return_counts=True)
            # add predicted class
            ind = np.where(occurence == np.amax(occurence))[0]
            if ind.shape:
                ind = ind[0]
            label = labels[ind]
            prediction[i] = label

        return prediction


if __name__ == '__main__':
    """
    Simple test dataset from sci-learn documentation.
    """
    knn = KNearestNeighbors(3)
    # points
    X = np.array([[0], [1], [2], [3]])
    # classes for each point
    y = np.array([0, 0, 1, 1])

    print('-------------------------------------\nTest dataset:')
    print('-------------------------------------')
    print('Training data:\n', X)
    print('Target values:\n', y)

    knn.fit(X, y)
    print('Prediction:\n', knn.predict(np.array([[1.1]])))

    """
    Using wine dataset.
    """
    knn1 = KNearestNeighbors(7)
    knn_check = KNeighborsClassifier(n_neighbors=7)
    wine = load_wine()
    X_train, X_test, y_train, y_test = train_test_split(wine.data, wine.target, test_size=0.3)

    knn1.fit(X_train, y_train)
    knn_check.fit(X_train, y_train)

    y_pred = knn1.predict(X_test)
    y_pred_check = knn_check.predict(X_test)

    print('\n-------------------------------------\nWine dataset:')
    print('-------------------------------------')
    print('Prediction:\n', y_pred)
    print('Correct:\n', y_pred_check)
    print('Accuracy:', metrics.accuracy_score(y_test, y_pred))
    print('Correct acc:', metrics.accuracy_score(y_test, y_pred_check))

    """
    Data visualization.
    """
    dict_data = load_wine(as_frame=True)
    data = dict_data['data']
    data['target_class'] = dict_data['target']
    # print(type(data))
    visualization_data = data[['od280/od315_of_diluted_wines', 'flavanoids', 'target_class']]

    c0 = data[data['target_class'] == 0]
    c1 = data[data['target_class'] == 1]
    c2 = data[data['target_class'] == 2]

    x0, y0 = c0['od280/od315_of_diluted_wines'], c0['flavanoids']
    x1, y1 = c1['od280/od315_of_diluted_wines'], c1['flavanoids']
    x2, y2 = c2['od280/od315_of_diluted_wines'], c2['flavanoids']

    plt.scatter(x0, y0, marker='^')
    plt.scatter(x1, y1, marker='x')
    plt.scatter(x2, y2, marker='o')
    plt.show()
