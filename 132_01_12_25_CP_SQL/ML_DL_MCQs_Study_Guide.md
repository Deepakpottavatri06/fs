# Machine Learning & Deep Learning MCQs Study Guide

## Question 1
**A decision tree tends to overfit when:**

| Option | Answer |
|--------|--------|
| a. | The number of classes increases |
| b. | Gini impurity is used |
| c. | The dataset is small |
| **d.** | **The tree depth is very large ✓** |

### Concept:
A very deep decision tree creates extremely specific rules that perfectly fit the training data, including noise. This leads to:
- Too many splits based on minor variations
- Poor generalization to new data
- High variance, low bias

### Why others are wrong:
- **a. The number of classes increases**: More classes don't directly cause overfitting; complexity of decision boundaries matters
- **b. Gini impurity is used**: Gini is just a splitting criterion, similar to entropy; doesn't cause overfitting
- **c. The dataset is small**: Small datasets can contribute to overfitting, but the primary factor is tree depth/complexity

---

## Question 2
**A large learning rate usually causes:**

| Option | Answer |
|--------|--------|
| a. | Faster convergence |
| b. | Reduced training time without issues |
| c. | Increased accuracy |
| **d.** | **Overshooting and divergence ✓** |

### Concept:
A large learning rate causes:
- **Overshooting**: Steps are too large, jumping past the optimal minimum
- **Divergence**: Loss increases instead of decreasing
- **Oscillation**: Bouncing around the minimum without converging

The learning rate controls the step size: `new_weight = old_weight - learning_rate × gradient`

### Why others are wrong:
- **a. Faster convergence**: May seem faster initially but often fails to converge at all
- **b. Reduced training time without issues**: Causes instability, not smooth training
- **c. Increased accuracy**: Usually decreases accuracy due to inability to find optimal weights

---

## Question 3
**A perceptron fails when data is:**

| Option | Answer |
|--------|--------|
| **a.** | **Non-linearly separable ✓** |
| b. | Centered around zero |
| c. | High-dimensional |
| d. | Linearly separable |

### Concept:
A single-layer perceptron can only learn **linear decision boundaries**. Classic example: XOR problem cannot be solved by a single perceptron because XOR is not linearly separable.

Perceptron equation: `y = sign(w·x + b)` creates a hyperplane that cannot curve.

### Why others are wrong:
- **b. Centered around zero**: Centering data often helps training, doesn't cause failure
- **c. High-dimensional**: Perceptrons can handle high dimensions; they just draw hyperplanes
- **d. Linearly separable**: This is exactly what perceptrons CAN solve successfully

---

## Question 4
**Batch size affects training primarily by influencing:**

| Option | Answer |
|--------|--------|
| a. | Size of the convolution kernel |
| b. | Activation functions |
| **c.** | **Gradient noise and stability ✓** |
| d. | Number of hidden layers |

### Concept:
Batch size determines how many samples are used to compute each gradient update:
- **Small batch**: More noise in gradients → can escape local minima but unstable
- **Large batch**: Smoother gradients → stable but may get stuck in sharp minima
- Affects memory usage and training dynamics

### Why others are wrong:
- **a. Size of the convolution kernel**: Kernel size is a hyperparameter, independent of batch size
- **b. Activation functions**: Activation functions are architectural choices, not affected by batch size
- **d. Number of hidden layers**: Architecture is fixed regardless of batch size

---

## Question 5
**In NLP, the purpose of positional encoding in Transformers is to:**

| Option | Answer |
|--------|--------|
| **a.** | **Inject sequence order information ✓** |
| b. | Add syntactic structure |
| c. | Improve tokenization |
| d. | Reduce vocabulary size |

### Concept:
Transformers process all tokens in parallel (unlike RNNs), so they have no inherent notion of word order. Positional encoding:
- Adds sine/cosine functions of different frequencies
- Encodes position of each token in the sequence
- Allows model to understand "first word" vs "last word"

Formula: `PE(pos, 2i) = sin(pos/10000^(2i/d_model))`

### Why others are wrong:
- **b. Add syntactic structure**: Positional encoding adds position, not grammar/syntax
- **c. Improve tokenization**: Tokenization happens before positional encoding
- **d. Reduce vocabulary size**: Has no effect on vocabulary

---

## Question 6
**In reinforcement learning, an episode ends when:**

| Option | Answer |
|--------|--------|
| **a.** | **A terminal state is reached ✓** |
| b. | Policy becomes random |
| c. | Reward becomes zero |
| d. | Learning rate becomes zero |

### Concept:
An episode is a complete sequence from initial state to terminal state:
- **Terminal states**: Goal reached, game over, failure condition
- Examples: Robot reaches target, game character dies, maze is solved
- After terminal state, environment resets for new episode

### Why others are wrong:
- **b. Policy becomes random**: Policy evolves during training but doesn't end episodes
- **c. Reward becomes zero**: Zero reward is just a reward value, not an ending condition
- **d. Learning rate becomes zero**: Learning rate is a hyperparameter, not related to episode termination

---

## Question 7
**In reinforcement learning, the value function represents:**

| Option | Answer |
|--------|--------|
| a. | Opponent action |
| b. | Immediate reward |
| c. | Difference between predicted and actual reward |
| **d.** | **Future expected return from a state ✓** |

### Concept:
Value function V(s) represents the **expected cumulative future reward** starting from state s:
- `V(s) = E[R_t + γR_{t+1} + γ²R_{t+2} + ...]`
- Considers discounted future rewards (γ = discount factor)
- Helps agent evaluate "how good" a state is long-term

### Why others are wrong:
- **a. Opponent action**: Not related to value function
- **b. Immediate reward**: That's just R_t; value function considers ALL future rewards
- **c. Difference between predicted and actual reward**: That's the TD error, not value function

---

## Question 8
**K-Means clustering optimizes which objective?**

| Option | Answer |
|--------|--------|
| a. | Maximum likelihood |
| b. | Classification error |
| c. | Gradient norm |
| **d.** | **Sum of squared distances within clusters ✓** |

### Concept:
K-Means minimizes **Within-Cluster Sum of Squares (WCSS)** or **inertia**:
- `J = Σ Σ ||x_i - μ_k||²`
- Iteratively assigns points to nearest centroid
- Updates centroids as cluster means
- Converges when assignments don't change

### Why others are wrong:
- **a. Maximum likelihood**: Used in probabilistic models like GMM, not K-Means
- **b. Classification error**: K-Means is unsupervised, no classification labels
- **c. Gradient norm**: Not the objective; K-Means uses coordinate descent, not gradient

---

## Question 9
**L2 regularization penalizes:**

| Option | Answer |
|--------|--------|
| **a.** | **Large squared weights ✓** |
| b. | Large absolute weights |
| c. | Input features |
| d. | Number of layers |

### Concept:
L2 Regularization (Ridge):
- Adds penalty term: `λ Σ w_i²`
- Penalizes the **sum of squared weights**
- Encourages smaller, distributed weights
- Prevents overfitting by reducing model complexity

Loss = Original Loss + λ||w||²

### Why others are wrong:
- **b. Large absolute weights**: That's L1 regularization (Lasso): `λ Σ |w_i|`
- **c. Input features**: Regularization affects weights, not input features
- **d. Number of layers**: Architecture choice, not affected by regularization

---

## Question 10
**Latent space in an autoencoder represents:**

| Option | Answer |
|--------|--------|
| **a.** | **Compressed intermediate representation of data ✓** |
| b. | Loss over epochs |
| c. | Error gradient |
| d. | The final predictions |

### Concept:
Autoencoder structure:
- **Encoder**: Input → Latent Space (bottleneck)
- **Decoder**: Latent Space → Reconstructed Output

Latent space is:
- Lower-dimensional representation capturing essential features
- Compressed encoding of input data
- Used for dimensionality reduction, denoising, generation

### Why others are wrong:
- **b. Loss over epochs**: Loss is a training metric, not the latent space
- **c. Error gradient**: Gradients are used for backpropagation
- **d. The final predictions**: Output layer produces predictions, not latent space

---

## Question 11
**RNNs mainly struggle with long-term dependencies due to:**

| Option | Answer |
|--------|--------|
| a. | Low memory |
| b. | Overfitting |
| **c.** | **Vanishing and exploding gradients ✓** |
| d. | Slow inference |

### Concept:
When backpropagating through many time steps:
- **Vanishing gradients**: Gradients shrink exponentially → early inputs have negligible effect
- **Exploding gradients**: Gradients grow exponentially → unstable training

This makes it hard for RNNs to learn relationships between distant tokens.

Solutions: LSTM, GRU (use gating mechanisms)

### Why others are wrong:
- **a. Low memory**: RNNs do maintain hidden state; the issue is gradient flow
- **b. Overfitting**: A general problem, not specific to long-term dependencies
- **d. Slow inference**: Sequential nature causes slow inference, but that's not the dependency issue

---

## Question 12
**The key mechanism in Transformers that replaced recurrence is:**

| Option | Answer |
|--------|--------|
| **a.** | **Self-attention ✓** |
| b. | Skip connections |
| c. | Autoencoders |
| d. | Max pooling |

### Concept:
Self-Attention (Scaled Dot-Product Attention):
- Computes relationships between ALL positions simultaneously
- `Attention(Q,K,V) = softmax(QK^T/√d_k)V`
- Enables parallel processing (unlike sequential RNNs)
- Captures long-range dependencies effectively

### Why others are wrong:
- **b. Skip connections**: Used in ResNets for gradient flow, not replacing recurrence
- **c. Autoencoders**: Different architecture for reconstruction/generation
- **d. Max pooling**: Used in CNNs for downsampling

---

## Question 13
**The output of the softmax layer is best described as:**

| Option | Answer |
|--------|--------|
| a. | A residual connection |
| b. | Binary classification result |
| **c.** | **Normalized probability distribution ✓** |
| d. | One-hot encoded vector |

### Concept:
Softmax function:
- `softmax(z_i) = e^(z_i) / Σ e^(z_j)`
- Converts logits to probabilities
- All outputs sum to 1
- Each output is between 0 and 1
- Used for multi-class classification

### Why others are wrong:
- **a. A residual connection**: Skip connections for gradient flow, unrelated
- **b. Binary classification result**: That would be sigmoid, not softmax
- **d. One-hot encoded vector**: One-hot has exactly one 1 and rest 0s; softmax has continuous probabilities

---

## Question 14
**The primary reason CNNs outperform fully connected layers on images is:**

| Option | Answer |
|--------|--------|
| a. | They can artificially enlarge data |
| b. | They train faster |
| c. | They use more parameters |
| **d.** | **They exploit spatial locality via filters ✓** |

### Concept:
CNNs leverage:
- **Spatial locality**: Nearby pixels are related; filters capture local patterns
- **Parameter sharing**: Same filter applied across entire image
- **Translation invariance**: Detect features regardless of position
- **Hierarchical features**: Edges → Textures → Objects

### Why others are wrong:
- **a. They can artificially enlarge data**: Data augmentation is separate from CNN architecture
- **b. They train faster**: Not inherently faster; efficiency comes from fewer parameters
- **c. They use more parameters**: Actually use FEWER parameters due to weight sharing

---

## Question 15
**The purpose of residual connections in deep networks is to:**

| Option | Answer |
|--------|--------|
| a. | Perform pooling |
| b. | Reduce the number of parameters |
| **c.** | **Prevent vanishing gradients and improve training ✓** |
| d. | Increase model depth without issues |

### Concept:
Residual (Skip) Connections:
- `output = F(x) + x` (identity shortcut)
- Gradients flow directly through skip connections
- Easier to learn identity mapping if layer is unnecessary
- Enables training of very deep networks (ResNet: 152+ layers)

### Why others are wrong:
- **a. Perform pooling**: Pooling reduces spatial dimensions; residual connections don't
- **b. Reduce the number of parameters**: Actually adds minimal parameters for skip
- **d. Increase model depth without issues**: Related but the PRIMARY purpose is gradient flow

---

## Question 16
**The universal approximation theorem states that:**

| Option | Answer |
|--------|--------|
| **a.** | **A neural network can approximate any continuous function ✓** |
| b. | More neurons always improve accuracy |
| c. | Neural networks can memorize any dataset |
| d. | Deep networks always outperform shallow networks |

### Concept:
Universal Approximation Theorem:
- A feedforward network with ONE hidden layer and sufficient neurons
- Can approximate any continuous function on compact subsets of ℝⁿ
- With arbitrary precision (given enough neurons)
- Theoretical guarantee, doesn't specify how many neurons needed

### Why others are wrong:
- **b. More neurons always improve accuracy**: Can lead to overfitting
- **c. Neural networks can memorize any dataset**: Memorization ≠ approximation; not generalization
- **d. Deep networks always outperform shallow networks**: Not always; depends on problem

---

## Question 17
**What does tokenization do in NLP?**

| Option | Answer |
|--------|--------|
| a. | Removes stop words |
| **b.** | **Splits text into smaller units (words/subwords) ✓** |
| c. | Converts text to lower case |
| d. | Parses grammar |

### Concept:
Tokenization:
- Breaks text into tokens (words, subwords, or characters)
- Examples: "Hello world" → ["Hello", "world"]
- Subword tokenization: BPE, WordPiece, SentencePiece
- Essential first step for any NLP pipeline

### Why others are wrong:
- **a. Removes stop words**: Stop word removal is a separate preprocessing step
- **c. Converts text to lower case**: Lowercasing is separate normalization
- **d. Parses grammar**: Parsing is syntactic analysis, done after tokenization

---

## Question 18
**Which activation function can output negative values?**

| Option | Answer |
|--------|--------|
| **a.** | **Tanh ✓** |
| b. | Softmax |
| c. | ReLU |
| d. | Sigmoid |

### Concept:
Tanh (Hyperbolic Tangent):
- `tanh(x) = (e^x - e^(-x)) / (e^x + e^(-x))`
- Output range: **[-1, 1]**
- Zero-centered, which helps optimization
- Can output negative values

### Why others are wrong:
- **b. Softmax**: Outputs probabilities in range [0, 1]
- **c. ReLU**: `max(0, x)` → Output range: [0, ∞), never negative
- **d. Sigmoid**: `1/(1+e^(-x))` → Output range: (0, 1), always positive

---

## Question 19
**Which ML technique is most suitable for detecting outliers?**

| Option | Answer |
|--------|--------|
| a. | PCA |
| **b.** | **Isolation Forest ✓** |
| c. | SVM (linear) |
| d. | Linear Regression |

### Concept:
Isolation Forest:
- Specifically designed for anomaly/outlier detection
- Isolates observations by random feature selection and splitting
- Outliers are isolated quickly (fewer splits needed)
- Anomaly score based on path length in trees

### Why others are wrong:
- **a. PCA**: Dimensionality reduction; can help visualize outliers but not designed for detection
- **c. SVM (linear)**: Classification algorithm; One-Class SVM can detect outliers but linear SVM is not optimal
- **d. Linear Regression**: Prediction algorithm, not for outlier detection

---

## Question 20
**Which of the following algorithms assumes features are conditionally independent?**

| Option | Answer |
|--------|--------|
| a. | KNN |
| b. | Logistic Regression |
| c. | Random Forest |
| **d.** | **Naive Bayes ✓** |

### Concept:
Naive Bayes:
- "Naive" assumption: All features are conditionally independent given the class
- `P(X|Y) = P(x₁|Y) × P(x₂|Y) × ... × P(xₙ|Y)`
- Simplifies computation significantly
- Works surprisingly well despite the unrealistic assumption

### Why others are wrong:
- **a. KNN**: Distance-based, no independence assumption
- **b. Logistic Regression**: Models feature weights, no independence assumption
- **c. Random Forest**: Ensemble of trees, considers feature interactions

---

## Question 21
**Which of the following is a non-linear kernel for SVM?**

| Option | Answer |
|--------|--------|
| **a.** | **Polynomial ✓** |
| b. | Hard margin |
| c. | Linear |
| d. | Logistic |

### Concept:
Polynomial Kernel:
- `K(x, y) = (x·y + c)^d`
- Maps data to higher-dimensional space
- Creates non-linear decision boundaries
- Other non-linear kernels: RBF (Gaussian), Sigmoid

### Why others are wrong:
- **b. Hard margin**: SVM variant (no slack), not a kernel
- **c. Linear**: `K(x, y) = x·y` creates linear boundaries
- **d. Logistic**: Not a standard SVM kernel; logistic is an activation function

---

## Question 22
**Which of the following is NOT a feature scaling method?**

| Option | Answer |
|--------|--------|
| a. | Standardization |
| b. | Log scaling |
| **c.** | **Label encoding ✓** |
| d. | Min–max normalization |

### Concept:
Label Encoding:
- Converts categorical labels to numeric values (e.g., "red"→0, "blue"→1)
- NOT a scaling method; it's a categorical encoding technique
- Doesn't transform numeric feature ranges

### Why others are wrong:
- **a. Standardization**: `(x - μ) / σ` → mean=0, std=1 (IS scaling)
- **b. Log scaling**: `log(x)` → compresses range (IS scaling)
- **d. Min-max normalization**: `(x - min) / (max - min)` → [0,1] (IS scaling)

---

## Question 23
**Which optimization algorithm adapts the learning rate differently for each parameter?**

| Option | Answer |
|--------|--------|
| **a.** | **Adam ✓** |
| b. | Momentum |
| c. | SGD |
| d. | SVM |

### Concept:
Adam (Adaptive Moment Estimation):
- Maintains per-parameter learning rates
- Combines momentum (first moment) and RMSprop (second moment)
- Adapts learning rate based on gradient history
- `lr_i = lr / (√v_i + ε)` where v_i is exponential moving average of squared gradients

### Why others are wrong:
- **b. Momentum**: Single learning rate with velocity term
- **c. SGD**: Single fixed learning rate for all parameters
- **d. SVM**: Not an optimizer; it's a classification algorithm

---

## Question 24
**Which type of neural network layer reduces the spatial size of feature maps?**

| Option | Answer |
|--------|--------|
| **a.** | **Max Pooling layer ✓** |
| b. | Recurrent layer |
| c. | Convolutional layer |
| d. | Fully connected layer |

### Concept:
Max Pooling:
- Downsamples feature maps by taking maximum in each window
- Reduces spatial dimensions (height × width)
- Provides translation invariance
- Common: 2×2 pooling with stride 2 → halves dimensions

### Why others are wrong:
- **b. Recurrent layer**: Processes sequences, not spatial reduction
- **c. Convolutional layer**: Can reduce size with stride, but primary purpose is feature extraction
- **d. Fully connected layer**: Flattens spatial dimensions, doesn't reduce in typical sense

---

## Question 25
**Word2Vec's Skip-Gram model predicts:**

| Option | Answer |
|--------|--------|
| a. | Center word from context |
| b. | Document embeddings |
| **c.** | **Context words from center word ✓** |
| d. | Both simultaneously |

### Concept:
Skip-Gram:
- Given center word, predict surrounding context words
- Input: "cat" → Output: ["the", "sat", "on", "mat"]
- Better for rare words and larger datasets
- Opposite of CBOW (Continuous Bag of Words)

### Why others are wrong:
- **a. Center word from context**: That's CBOW model
- **b. Document embeddings**: Word2Vec creates word embeddings; Doc2Vec does document embeddings
- **d. Both simultaneously**: Only predicts one direction

---

## Summary Table: Questions You Got Wrong

| Question | Topic | Correct Answer |
|----------|-------|----------------|
| Q1 | Decision Tree Overfitting | d. The tree depth is very large |
| Q3 | Perceptron Limitations | a. Non-linearly separable |
| Q7 | RL Value Function | d. Future expected return from a state |
| Q19 | Outlier Detection | b. Isolation Forest |

---

## Key Concepts Quick Reference

### Regularization
| Type | Penalty | Effect |
|------|---------|--------|
| L1 (Lasso) | Σ\|w\| | Sparse weights, feature selection |
| L2 (Ridge) | Σw² | Small distributed weights |
| Dropout | Random neuron removal | Prevents co-adaptation |

### Activation Functions
| Function | Range | Use Case |
|----------|-------|----------|
| Sigmoid | (0, 1) | Binary classification output |
| Tanh | (-1, 1) | Hidden layers (zero-centered) |
| ReLU | [0, ∞) | Hidden layers (default choice) |
| Softmax | (0, 1), sum=1 | Multi-class classification output |

### Optimization Algorithms
| Algorithm | Key Feature |
|-----------|-------------|
| SGD | Basic gradient descent |
| Momentum | Velocity term for faster convergence |
| RMSprop | Adaptive learning rate (squared gradients) |
| Adam | Momentum + RMSprop combined |

### Word Embeddings
| Model | Input → Output |
|-------|----------------|
| Skip-Gram | Center word → Context words |
| CBOW | Context words → Center word |

### Neural Network Architectures
| Type | Best For |
|------|----------|
| CNN | Images, spatial data |
| RNN/LSTM/GRU | Sequential data, time series |
| Transformer | NLP, long sequences (parallel) |
| Autoencoder | Dimensionality reduction, generation |
