import lightgbm as lgb
import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.metrics import f1_score
import xgboost as xgb
train = pd.read_csv('./credit_train.csv')
test = pd.read_csv('./credit_test.csv')
print (train.shape)


train = train[train.all_bal != '\\N']
# test = test[test.all_bal != '\\N']
train['all_bal'] = train['all_bal'].apply(lambda x : float(x))
test['all_bal'] = test['all_bal'].apply(lambda x : float(x))

train = train[train.bad_bal != '\\N']
# test = test[test.bad_bal != '\\N']
train['bad_bal'] = train['bad_bal'].apply(lambda x : float(x))
test['bad_bal'] = test['bad_bal'].apply(lambda x : float(x))

train = train[train.due_intr != '\\N']
# test = test[test.bad_bal != '\\N']
train['due_intr'] = train['due_intr'].apply(lambda x : float(x))
test['due_intr'] = test['due_intr'].apply(lambda x : float(x))

train = train[train.norm_bal != '\\N']
# test = test[test.norm_bal != '\\N']
train['norm_bal'] = train['norm_bal'].apply(lambda x : float(x))
test['norm_bal'] = test['norm_bal'].apply(lambda x : float(x))

train = train[train.delay_bal != '\\N']
# test = test[test.delay_bal != '\\N']
train['delay_bal'] = train['delay_bal'].apply(lambda x : float(x))
test['delay_bal'] = test['delay_bal'].apply(lambda x : float(x))

train = train[train.is_employee != '\\N']
# test = test[test.is_employee != '\\N']
train['is_employee'] = train['is_employee'].apply(lambda x : int(x))
test['is_employee'] = test['is_employee'].apply(lambda x : int(x))

train = train[train.is_shareholder != '\\N']
# test = test[test.is_shareholder != '\\N']
train['is_shareholder'] = train['is_shareholder'].apply(lambda x : int(x))
test['is_shareholder'] = test['is_shareholder'].apply(lambda x : int(x))

train = train[train.is_contact != '\\N']
# test = test[test.is_contact != '\\N']
train['is_contact'] = train['is_contact'].apply(lambda x : int(x))
test['is_contact'] = test['is_contact'].apply(lambda x : int(x))

train = train[train.is_mgr_dep != '\\N']
# test = test[test.is_mgr_dep != '\\N']
train['is_mgr_dep'] = train['is_mgr_dep'].apply(lambda x : int(x))
test['is_mgr_dep'] = test['is_mgr_dep'].apply(lambda x : int(x))

train = train[train.cred_limit != '\\N']
# test = test[test.cred_limit!= '\\N']
train['cred_limit'] = train['cred_limit'].apply(lambda x : float(x))
test['cred_limit'] = test['cred_limit'].apply(lambda x : float(x))

train = train[train.over_draft != '\\N']
# test = test[test.over_draft!= '\\N']
train['over_draft'] = train['over_draft'].apply(lambda x : float(x))
test['over_draft'] = test['over_draft'].apply(lambda x : float(x))

train = train[train.over_draft != '\\N']
# test = test[test.over_draft!= '\\N']
train['over_draft'] = train['over_draft'].apply(lambda x : float(x))
test['over_draft'] = test['over_draft'].apply(lambda x : float(x))

train = train[train.five_class != '\\N']
# test = test[test.five_class!= '\\N']
train['five_class'] = train['five_class'].apply(lambda x : int(x))
test['five_class'] = test['five_class'].apply(lambda x : int(x))


#选择标签名
label = train.pop('credit_level')
le = LabelEncoder()
label = le.fit_transform(label)


#选择要处理的列
feature = [value for value in train.columns.values if
                   value not in ['uid']]



#xgb模型
def XGB():
    clf = xgb.XGBClassifier(max_depth=12, learning_rate=0.05,
                            n_estimators=10000, silent=True,
                            objective="multi:softmax",
                            nthread=4, gamma=0,
                            max_delta_step=0, subsample=1, colsample_bytree=0.9, colsample_bylevel=0.9,
                            reg_alpha=1, reg_lambda=1, scale_pos_weight=1,
                            base_score=0.5, seed=2018, num_class=5)
    return clf




#online判断是输出文档还是计算损失。
online = False
if online:
        print ('online')

        model = XGB()
        model.fit(train[feature], label, eval_set=[(train[feature], label)], verbose=1,)
        pred = model.predict(test[feature])
        pred = le.inverse_transform(pred)
        test['predict'] = pred

        test[['uid', 'predict']].to_csv('./credit_submission.csv', index=False)
else:
        print ('offline')
        train_x,test_x,train_y,test_y = train_test_split(train[feature],label,test_size=0.1,shuffle=True,random_state=2018)
        model = XGB()
        model.fit(train_x[feature], train_y, eval_set=[(test_x[feature], test_y)], verbose=1,early_stopping_rounds=100)
        pred = model.predict(test_x)
        print(f1_score(test_y,pred,average='weighted'))
