package com.fc.aden.model.custom.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 打印历史Example实体类
 *
 * @author Created by zc on 2019/7/3
 */
public class PrintHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrintHistoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("items_code is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("items_code is not null");
            return (Criteria) this;
        }


        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("items_code >=", value, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("items_code <", value, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("items_code <=", value, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("items_code like", value, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("items_code not like", value, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("items_code in", values, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("items_code not in", values, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("items_code between", value1, value2, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("items_code not between", value1, value2, "itemsCode");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIsNull() {
            addCriterion("original_id is null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIsNotNull() {
            addCriterion("original_id is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdEqualTo(String value) {
            addCriterion("original_id =", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotEqualTo(String value) {
            addCriterion("original_id <>", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThan(String value) {
            addCriterion("original_id >", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThanOrEqualTo(String value) {
            addCriterion("original_id >=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThan(String value) {
            addCriterion("original_id <", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThanOrEqualTo(String value) {
            addCriterion("original_id <=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLike(String value) {
            addCriterion("original_id like", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotLike(String value) {
            addCriterion("original_id not like", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIn(List<String> values) {
            addCriterion("original_id in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotIn(List<String> values) {
            addCriterion("original_id not in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdBetween(String value1, String value2) {
            addCriterion("original_id between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotBetween(String value1, String value2) {
            addCriterion("original_id not between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdIsNull() {
            addCriterion("print_lable_id is null");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdIsNotNull() {
            addCriterion("print_lable_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdEqualTo(String value) {
            addCriterion("print_lable_id =", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdNotEqualTo(String value) {
            addCriterion("print_lable_id <>", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdGreaterThan(String value) {
            addCriterion("print_lable_id >", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdGreaterThanOrEqualTo(String value) {
            addCriterion("print_lable_id >=", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdLessThan(String value) {
            addCriterion("print_lable_id <", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdLessThanOrEqualTo(String value) {
            addCriterion("print_lable_id <=", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdLike(String value) {
            addCriterion("print_lable_id like", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdNotLike(String value) {
            addCriterion("print_lable_id not like", value, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdIn(List<String> values) {
            addCriterion("print_lable_id in", values, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdNotIn(List<String> values) {
            addCriterion("print_lable_id not in", values, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdBetween(String value1, String value2) {
            addCriterion("print_lable_id between", value1, value2, "printLableId");
            return (Criteria) this;
        }

        public Criteria andPrintLableIdNotBetween(String value1, String value2) {
            addCriterion("print_lable_id not between", value1, value2, "printLableId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIsNull() {
            addCriterion("product_category is null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIsNotNull() {
            addCriterion("product_category is not null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryEqualTo(String value) {
            addCriterion("product_category =", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotEqualTo(String value) {
            addCriterion("product_category <>", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryGreaterThan(String value) {
            addCriterion("product_category >", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("product_category >=", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryLessThan(String value) {
            addCriterion("product_category <", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryLessThanOrEqualTo(String value) {
            addCriterion("product_category <=", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryLike(String value) {
            addCriterion("product_category like", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotLike(String value) {
            addCriterion("product_category not like", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIn(List<String> values) {
            addCriterion("product_category in", values, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotIn(List<String> values) {
            addCriterion("product_category not in", values, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryBetween(String value1, String value2) {
            addCriterion("product_category between", value1, value2, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotBetween(String value1, String value2) {
            addCriterion("product_category not between", value1, value2, "productCategory");
            return (Criteria) this;
        }

        public Criteria andCorrectStageIsNull() {
            addCriterion("correct_stage is null");
            return (Criteria) this;
        }

        public Criteria andCorrectStageIsNotNull() {
            addCriterion("correct_stage is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectStageEqualTo(String value) {
            addCriterion("correct_stage =", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageNotEqualTo(String value) {
            addCriterion("correct_stage <>", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageGreaterThan(String value) {
            addCriterion("correct_stage >", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageGreaterThanOrEqualTo(String value) {
            addCriterion("correct_stage >=", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageLessThan(String value) {
            addCriterion("correct_stage <", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageLessThanOrEqualTo(String value) {
            addCriterion("correct_stage <=", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageLike(String value) {
            addCriterion("correct_stage like", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageNotLike(String value) {
            addCriterion("correct_stage not like", value, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageIn(List<String> values) {
            addCriterion("correct_stage in", values, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageNotIn(List<String> values) {
            addCriterion("correct_stage not in", values, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageBetween(String value1, String value2) {
            addCriterion("correct_stage between", value1, value2, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStageNotBetween(String value1, String value2) {
            addCriterion("correct_stage not between", value1, value2, "correctStage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageIsNull() {
            addCriterion("correct_storage is null");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageIsNotNull() {
            addCriterion("correct_storage is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageEqualTo(String value) {
            addCriterion("correct_storage =", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageNotEqualTo(String value) {
            addCriterion("correct_storage <>", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageGreaterThan(String value) {
            addCriterion("correct_storage >", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageGreaterThanOrEqualTo(String value) {
            addCriterion("correct_storage >=", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageLessThan(String value) {
            addCriterion("correct_storage <", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageLessThanOrEqualTo(String value) {
            addCriterion("correct_storage <=", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageLike(String value) {
            addCriterion("correct_storage like", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageNotLike(String value) {
            addCriterion("correct_storage not like", value, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageIn(List<String> values) {
            addCriterion("correct_storage in", values, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageNotIn(List<String> values) {
            addCriterion("correct_storage not in", values, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageBetween(String value1, String value2) {
            addCriterion("correct_storage between", value1, value2, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andCorrectStorageNotBetween(String value1, String value2) {
            addCriterion("correct_storage not between", value1, value2, "correctStorage");
            return (Criteria) this;
        }

        public Criteria andEmployerNameIsNull() {
            addCriterion("employer_name is null");
            return (Criteria) this;
        }

        public Criteria andEmployerNameIsNotNull() {
            addCriterion("employer_name is not null");
            return (Criteria) this;
        }

        public Criteria andEmployerNameEqualTo(String value) {
            addCriterion("employer_name =", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameNotEqualTo(String value) {
            addCriterion("employer_name <>", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameGreaterThan(String value) {
            addCriterion("employer_name >", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameGreaterThanOrEqualTo(String value) {
            addCriterion("employer_name >=", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameLessThan(String value) {
            addCriterion("employer_name <", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameLessThanOrEqualTo(String value) {
            addCriterion("employer_name <=", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameLike(String value) {
            addCriterion("employer_name like", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameNotLike(String value) {
            addCriterion("employer_name not like", value, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameIn(List<String> values) {
            addCriterion("employer_name in", values, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameNotIn(List<String> values) {
            addCriterion("employer_name not in", values, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameBetween(String value1, String value2) {
            addCriterion("employer_name between", value1, value2, "employerName");
            return (Criteria) this;
        }

        public Criteria andEmployerNameNotBetween(String value1, String value2) {
            addCriterion("employer_name not between", value1, value2, "employerName");
            return (Criteria) this;
        }

        public Criteria andPrintTimeIsNull() {
            addCriterion("print_time is null");
            return (Criteria) this;
        }

        public Criteria andPrintTimeIsNotNull() {
            addCriterion("print_time is not null");
            return (Criteria) this;
        }

        public Criteria andPrintTimeEqualTo(Date value) {
            addCriterion("print_time =", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeNotEqualTo(Date value) {
            addCriterion("print_time <>", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeGreaterThan(Date value) {
            addCriterion("print_time >", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("print_time >=", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeLessThan(Date value) {
            addCriterion("print_time <", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeLessThanOrEqualTo(Date value) {
            addCriterion("print_time <=", value, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeIn(List<Date> values) {
            addCriterion("print_time in", values, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeNotIn(List<Date> values) {
            addCriterion("print_time not in", values, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeBetween(Date value1, Date value2) {
            addCriterion("print_time between", value1, value2, "printTime");
            return (Criteria) this;
        }

        public Criteria andPrintTimeNotBetween(Date value1, Date value2) {
            addCriterion("print_time not between", value1, value2, "printTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}