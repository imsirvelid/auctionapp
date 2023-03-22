import moment from "moment";

export const getDateDiffFromToday = (end) => {
  var endDate = moment(end);
  var today = moment();
  var weeksBetween = endDate.diff(today, "week");
  today.add(weeksBetween, "weeks");
  return weeksBetween + " Weeks " + endDate.diff(today, "days") + " Days";
};
