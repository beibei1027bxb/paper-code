from data_processing.data_formatting_utils import subtokenize_comment, subtokenize_code, compute_code_diff_spans
from data_utils import DiffExample
from data_processing.method_details_extraction import extract_method_name, extract_return_type, \
    extract_return_statements
import json
import sys

sys.path.append('../')
from diff_utils import compute_minimal_comment_diffs, compute_code_diffs

#       update
with open('E:\\7_withContext.jsonl', 'r') as f:
    with open('E:\\experiment2.1\\update\\7_withContext.jsonl', 'w') as f1:
        with open('E:\\experiment2.1\\update\\7_method_details.json','w') as f2:
            with open('E:\\experiment2.1\\update\\7_tokenization_features.json','w') as f3:
                method_details = dict()
                tokenization_features = dict()
                count = 1
                line = f.readline()
                while line:
                    print(count)
                    data = json.loads(line)
                    old_nl = data['src_desc']
                    old_code = data['src_method']

                    new_nl = data['dst_desc']
                    new_code = data['dst_method']

                    old_nl_subtokens, old_nl_subtoken_labels, old_nl_subtoken_indices = subtokenize_comment(old_nl)
                    old_code_subtokens, old_code_subtoken_labels, old_code_subtoken_indices = subtokenize_code(old_code)

                    new_nl_subtokens, new_nl_subtoken_labels, new_nl_subtoken_indices = subtokenize_comment(new_nl)
                    new_code_subtokens, new_code_subtoken_labels, new_code_subtoken_indices = subtokenize_code(new_code)

                    span_diff_tokens, span_diff_labels, span_diff_indices = compute_code_diff_spans(
                        old_code_subtokens, old_code_subtoken_labels, old_code_subtoken_indices, new_code_subtokens,
                        new_code_subtoken_labels, new_code_subtoken_indices)

                    _, token_diff_tokens, _ = compute_code_diffs(old_code_subtokens, new_code_subtokens)

                    comment_edit_spans, _, _ = compute_minimal_comment_diffs(old_nl_subtokens, new_nl_subtokens)

                    example = DiffExample(
                        id=data['sample_id'],
                        old_comment=' '.join(old_nl_subtokens),
                        old_comment_tokens=old_nl_subtokens,
                        new_comment=' '.join(new_nl_subtokens),
                        new_comment_tokens=new_nl_subtokens,
                        old_code=' '.join(old_code_subtokens),
                        old_code_tokens=old_code_subtokens,
                        new_code=' '.join(new_code_subtokens),
                        new_code_tokens=new_code_subtokens,
                        span_diff_code=' '.join(span_diff_tokens),
                        span_diff_code_tokens=span_diff_tokens,
                        span_minimal_diff_comment=' '.join(comment_edit_spans),
                        span_minimal_diff_comment_tokens=comment_edit_spans,
                        token_diff_code_tokens=token_diff_tokens
                    )
                    ###
                    result = dict()
                    result['id'] = example.id
                    result['old_comment'] = example.old_comment
                    result['old_comment_tokens'] = example.old_comment_tokens
                    result['new_comment'] = example.new_comment
                    result['new_comment_tokens'] = example.new_comment_tokens
                    result['old_code'] = example.old_code
                    result['old_code_tokens'] = example.old_code_tokens
                    result['new_code'] = example.new_code
                    result['new_code_tokens'] = example.new_code_tokens
                    result['span_diff_code'] = example.span_diff_code
                    result['span_diff_code_tokens'] = example.span_diff_code_tokens
                    result['span_minimal_diff_comment'] = example.span_minimal_diff_comment
                    result['span_minimal_diff_comment_tokens'] = example.span_minimal_diff_comment_tokens
                    result['token_diff_code_tokens'] = example.token_diff_code_tokens
                    ###

                    result = json.dumps(result)
                    f1.write(result + ",")
                    # Should be stored in 'resources/method_details.json' for every example, by id

                    method_details[example.id] = dict()
                    # method_details[example.id]['method_name_subtokens'], _, _ = subtokenize_code(
                    #     extract_method_name(old_code.split('\n')))
                    method_details[example.id]['method_name_subtokens'] = []
                    # method_details[example.id]['old_return_type_subtokens'], _, _ = subtokenize_code(
                    #     extract_return_type(old_code.split('\n')))
                    method_details[example.id]['old_return_type_subtokens'] = []
                    # method_details[example.id]['return_type_subtokens'], _, _ = subtokenize_code(
                    #     extract_return_type(new_code.split('\n')))
                    method_details[example.id]['return_type_subtokens'] = []
                    # method_details[example.id]['old_return_sequence'], _, _ = subtokenize_code(
                    #     ' '.join(extract_return_statements(old_code.split('\n'))))
                    method_details[example.id]['old_return_sequence'] = []
                    # method_details[example.id]['new_return_sequence'], _, _ = subtokenize_code(
                    #     ' '.join(extract_return_statements(new_code.split('\n'))))
                    method_details[example.id]['new_return_sequence'] = []
                    method_details[example.id]['old_code'] = old_code
                    method_details[example.id]['new_code'] = new_code
                    method_details[example.id]['old_method_name_subtokens'] = []
                    method_details[example.id]['old_argument_full'] = []
                    method_details[example.id]['old_argument_identifiers'] = []
                    method_details[example.id]['new_argument_full'] = []
                    method_details[example.id]['new_argument_identifiers'] = []
                    method_details[example.id]['return_spans'] = []
                    method_details[example.id]['return_tokens'] = []
                    method_details[example.id]['return_commands'] = []

                    # Should be stored in 'resources/tokenization_features.json' for every example, by id

                    tokenization_features[example.id] = dict()
                    # tokenization_features[example.id]['edit_span_subtoken_labels'] = span_diff_labels
                    tokenization_features[example.id]['edit_span_subtoken_labels'] = []
                    # tokenization_features[example.id]['edit_span_subtoken_indices'] = span_diff_indices
                    tokenization_features[example.id]['edit_span_subtoken_indices'] = []
                    # tokenization_features[example.id]['old_nl_subtoken_labels'] = old_nl_subtoken_labels
                    tokenization_features[example.id]['old_nl_subtoken_labels'] =[]
                    # tokenization_features[example.id]['old_nl_subtoken_indices'] = old_nl_subtoken_indices
                    tokenization_features[example.id]['old_nl_subtoken_indices'] = []
                    tokenization_features[example.id]['edit_ast_features'] = []
                    line = f.readline()
                    count = count + 1
                    # if count == 5077:
                    #     line = f.readline()
                    #     count = count + 1
                f1.close()
                t1 = json.dumps(method_details)
                t2 = json.dumps(tokenization_features)
                f3.write(t2)
                f3.close()
            f2.write(t1)
            f2.close()
        f.close()
# generation
# with open('E:\\CUP\\dataset\\last_7_withoutRefactor.jsonl', 'r') as f:
#     with open('E:\\experiment2.2\\generation\\last_7_withoutRefactor_test2.jsonl', 'w') as f1:
#         count = 1
#         line = f.readline()
#         while line:
#             print(count)
#             data = json.loads(line)
#             old_nl = data['src_desc']
#             old_code = data['src_method']
#
#             new_nl = data['dst_desc']
#             new_code = data['dst_method']
#
#             old_nl_subtokens, old_nl_subtoken_labels, old_nl_subtoken_indices = subtokenize_comment(old_nl)
#             old_code_subtokens, old_code_subtoken_labels, old_code_subtoken_indices = subtokenize_code(old_code)
#
#             new_nl_subtokens, new_nl_subtoken_labels, new_nl_subtoken_indices = subtokenize_comment(new_nl)
#             new_code_subtokens, new_code_subtoken_labels, new_code_subtoken_indices = subtokenize_code(new_code)
#
#             span_diff_tokens, span_diff_labels, span_diff_indices = compute_code_diff_spans(
#                 old_code_subtokens, old_code_subtoken_labels, old_code_subtoken_indices, new_code_subtokens,
#                 new_code_subtoken_labels, new_code_subtoken_indices)
#
#             _, token_diff_tokens, _ = compute_code_diffs(old_code_subtokens, new_code_subtokens)
#
#             comment_edit_spans, _, _ = compute_minimal_comment_diffs(old_nl_subtokens, new_nl_subtokens)
#             result = dict()
#             example = DiffExample(
#                 id=data['sample_id'],
#                 old_comment=' '.join(old_nl_subtokens),
#                 old_comment_tokens=old_nl_subtokens,
#                 new_comment=' '.join(new_nl_subtokens),
#                 new_comment_tokens=new_nl_subtokens,
#                 old_code=' '.join(old_code_subtokens),
#                 old_code_tokens=old_code_subtokens,
#                 new_code=' '.join(new_code_subtokens),
#                 new_code_tokens=new_code_subtokens,
#                 span_diff_code=' '.join(span_diff_tokens),
#                 span_diff_code_tokens=span_diff_tokens,
#                 span_minimal_diff_comment=' '.join(comment_edit_spans),
#                 span_minimal_diff_comment_tokens=comment_edit_spans,
#                 token_diff_code_tokens=token_diff_tokens
#             )
#             result['id'] = example.id
#             result['old_comment'] = example.old_comment
#             result['old_comment_tokens'] = example.old_comment_tokens
#             result['new_comment'] = example.new_comment
#             result['new_comment_tokens'] = example.new_comment_tokens
#             result['old_code'] = example.old_code
#             result['old_code_tokens'] = example.old_code_tokens
#             result['new_code'] = example.new_code
#             result['new_code_tokens'] = example.new_code_tokens
#             f1.write(json.dumps(result) + ",")
#             line = f.readline()
#             count = count + 1
#     f1.close()
