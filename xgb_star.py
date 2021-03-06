import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.metrics import f1_score
import xgboost as xgb
train = pd.read_csv('./star_train.csv')
test = pd.read_csv('./star_test.csv')
print (train.shape)


train = train[train.is_employee != '\\N']
# test = test[test.is_employee != '\\N']
train['is_employee'] = train['is_employee'].apply(lambda x : int(x))
test['is_employee'] = test['is_employee'].apply(lambda x : int(x))

train = train[train.is_shareholder != '\\N']
# test = test[test.is_shareholder != '\\N']
train['is_shareholder'] = train['is_shareholder'].apply(lambda x : int(x))
test['is_shareholder'] = test['is_shareholder'].apply(lambda x : int(x))

train = train[train.is_contact != '\\N']
# test = test[test.bad_bal != '\\N']
train['is_contact'] = train['is_contact'].apply(lambda x : int(x))
test['is_contact'] = test['is_contact'].apply(lambda x : int(x))

train = train[train.is_mgr_dep != '\\N']
# test = test[test.norm_bal != '\\N']
train['is_mgr_dep'] = train['is_mgr_dep'].apply(lambda x : int(x))
test['is_mgr_dep'] = test['is_mgr_dep'].apply(lambda x : int(x))

train = train[train.all_bal != '\\N']
# test = test[test.all_bal != '\\N']
train['all_bal'] = train['all_bal'].apply(lambda x : float(x))
test['all_bal'] = test['all_bal'].apply(lambda x : float(x))

train = train[train.avg_mth != '\\N']
# test = test[test.avg_mth != '\\N']
train['avg_mth'] = train['avg_mth'].apply(lambda x : float(x))
test['avg_mth'] = test['avg_mth'].apply(lambda x : float(x))

train = train[train.avg_qur != '\\N']
# test = test[test.avg_qur != '\\N']
train['avg_qur'] = train['avg_qur'].apply(lambda x : float(x))
test['avg_qur'] = test['avg_qur'].apply(lambda x : float(x))

train = train[train.avg_year != '\\N']
# test = test[test.avg_year != '\\N']
train['avg_year'] = train['avg_year'].apply(lambda x : float(x))
test['avg_year'] = test['avg_year'].apply(lambda x : float(x))

train = train[train.deposit != '\\N']
# test = test[test.deposit != '\\N']
train['deposit'] = train['deposit'].apply(lambda x : float(x))
test['deposit'] = test['deposit'].apply(lambda x : float(x))

train = train[train.five_class != '\\N']
# test = test[test.five_class!= '\\N']
train['five_class'] = train['five_class'].apply(lambda x : int(x))
test['five_class'] = test['five_class'].apply(lambda x : int(x))

train = train[train.bal != '\\N']
# test = test[test.bal!= '\\N']
train['bal'] = train['bal'].apply(lambda x : float(x))
test['bal'] = test['bal'].apply(lambda x : float(x))


#???????????????
label = train.pop('star_level')
le = LabelEncoder()
label = le.fit_transform(label)


#?????????????????????
feature = [value for value in train.columns.values if
                   value not in ['uid']]



#xgb??????
def XGB():
    clf = xgb.XGBClassifier(max_depth=12, learning_rate=0.05,
                            n_estimators=10000, silent=True,
                            objective="multi:softmax",
                            nthread=4, gamma=0,
                            max_delta_step=0, subsample=1, colsample_bytree=0.9, colsample_bylevel=0.9,
                            reg_alpha=1, reg_lambda=1, scale_pos_weight=1,
                            base_score=0.5, seed=2018, num_class=9)
    return clf




#online??????????????????????????????????????????
online = False
if online:
        print ('online')

        model = XGB()
        model.fit(train[feature], label, eval_set=[(train[feature], label)], verbose=1,)
        pred = model.predict(test[feature])
        pred = le.inverse_transform(pred)
        test['predict'] = pred

        test[['uid', 'predict']].to_csv('./star_submission.csv', index=False)
else:
        print ('offline')
        train_x,test_x,train_y,test_y = train_test_split(train[feature],label,test_size=0.1,shuffle=True,random_state=2018)
        model = XGB()
        model.fit(train_x[feature], train_y, eval_set=[(test_x[feature], test_y)], verbose=1,early_stopping_rounds=100)
        pred = model.predict(test_x)
        print(f1_score(test_y,pred,average='weighted'))
