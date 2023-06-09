START = '<sos>'
END = '<eos>'
NL_EMBEDDING_SIZE = 64
CODE_EMBEDDING_SIZE = 64
HIDDEN_SIZE = 64
DECODER_HIDDEN_SIZE = 128
DROPOUT_RATE = 0.6
NUM_LAYERS = 2
LR = 0.001
BATCH_SIZE = 100
MAX_EPOCHS = 100
PATIENCE = 100
VOCAB_CUTOFF_PCT = 5
LENGTH_CUTOFF_PCT = 95
NUM_ENCODERS = 2
MAX_VOCAB_EXTENSION = 50
BEAM_SIZE = 20
MAX_RETURN_TYPE_LENGTH = 10
NL_EMBEDDING_PATH = 'embeddings/nl_embeddings.json'
CODE_EMBEDDING_PATH = 'embeddings/code_embeddings.json'
MAX_VOCAB_SIZE = 10000
PREDICTION_DIR = 'prediction_output'
FULL_GENERATION_MODEL_PATH = 'generation-models/model.pkl.gz'
MODEL_LAMBDA = 0.5
LIKELIHOOD_LAMBDA = 0.3
OLD_METEOR_LAMBDA = 0.2
GEN_MODEL_LAMBDA = 0.5
GEN_OLD_BLEU_LAMBDA = 0.5
