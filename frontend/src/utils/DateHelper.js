import moment from "moment";

export const getDateDiffFromToday = (end) => {
  const endDate = moment(end);
  const today = moment();
  console.log("Danas je: ", today);
  console.log("Menet interesuje: ", endDate);
  const weeksBetween = endDate.diff(today, "week");
  today.add(weeksBetween, "weeks");
  return weeksBetween + " Weeks " + endDate.diff(today, "days") + " Days";
};