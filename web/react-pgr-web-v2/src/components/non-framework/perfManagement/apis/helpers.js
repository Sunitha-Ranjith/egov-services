
var jp = require('jsonpath');

export const flattenArray = (array) => {
    let self = this;
    return array.reduce(function(memo, el) {
      var items = Array.isArray(el) ? flattenArray(el) : [el];
      return memo.concat(items);
    }, []);
}

export const getMonth = (month) => {
    switch(month) {
        case '1':
            return 'JANUNARY'
        case '2':
            return 'FEBRUARY'
        case '3':
            return 'MARCH'
        case '4':
            return 'APRIL'
        case '5':
            return 'MAY'
        case '6':
            return 'JUNE'
        case '7':
            return 'JULY'
        case '8':
            return 'AUGUST'
        case '9':
            return 'SEPTEMBER'
        case '10':
            return 'OCTOBER'
        case '11':
            return 'NOVEMBER'
        case '12':
            return 'DECEMBER'
    }
}

export const formatParsedChartData = (data, cb) => {
    let parsed = {
      data: data,
    };
    let chartData = [];
    let chartDataKey = '';
    let ulbs = [...new Set(jp.query(parsed, '$.data[*].ulbName'))];
    let finYears = [...new Set(jp.query(parsed, '$.data[*].finYear'))];
    let kpis = [...new Set(jp.query(parsed, '$.data[*].kpiName'))];

    if (kpis.length === 1) {
      if (finYears.length > 1 && ulbs.length > 1) {
        chartData = data.filter(el => el.ulbName === ulbs[0]);
        chartDataKey = 'finYear';
        return cb(chartData, chartDataKey);
      }
      if (finYears.length > 1 && ulbs.length === 1) {
        chartData = data;
        chartDataKey = 'finYear';
        return cb(chartData, chartDataKey);
      }
      if (finYears.length === 1 && ulbs.length > 1) {
        chartData = data;
        chartDataKey = 'ulbName';
        return cb(chartData, chartDataKey);
      }
      if (finYears.length === 1 && ulbs.length === 1) {
        chartData = data;
        chartDataKey = 'finYear';
        return cb(chartData, chartDataKey);
      }
    }

    if (kpis.length > 1) {
      if (finYears.length > 1 && ulbs.length > 1) {
        chartData = data.filter(el => el.ulbName === ulbs[0]);
        chartDataKey = 'finYear';
        return cb(chartData, chartDataKey);
      }
      if (finYears.length > 1 && ulbs.length === 1) {
        chartData = data.filter(el => el.finYear === finYears[0]);
        chartDataKey = 'finYear';
        return cb(chartData, chartDataKey);
      }
      if (finYears.length === 1 && ulbs.length > 1) {
        chartData = data.filter(el => el.ulbName === ulbs[0]);
        chartDataKey = 'finYear';
        return cb(chartData, chartDataKey);
      }
      if (finYears.length === 1 && ulbs.length === 1) {
        chartData = data;
        chartDataKey = 'kpiName';
        return cb(chartData, chartDataKey);
      }
    }
    return cb(null, null);
}

export const formatChartData = (data, cb) => {
    let parsed = {
      data: data
    }

    let ulbs        = [...new Set(jp.query(parsed, '$.data[*].ulbName'))];
    let finYears    = [...new Set(jp.query(parsed, '$.data[*].finYear'))];
    let array       = new Array()

    for (let index = 0; index < ulbs.length; index++) {
        const element = ulbs[index];
        for (let finYearIndex = 0; finYearIndex < finYears.length; finYearIndex++) {
            const element = finYears[finYearIndex];
            array.push({
                ulbName: ulbs[index],
                finYear: finYears[finYearIndex],
                data: data.filter(  (ele) => {
                    if ((ele.ulbName ===  ulbs[index]) && (ele.finYear === finYears[finYearIndex])) {
                        return ele;
                    }
                })
            })
        }
    }

    let parsedData = array.map((elem, index) => {
        return {
            ulbName: elem.ulbName,
            finYear: elem.finYear,
            data: elem.data.sort((obj1, obj2) => obj1.period - obj2.period)
        }
    })

    cb(parsedData, "monthlyValue")
  }