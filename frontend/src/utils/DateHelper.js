import moment from "moment";
import momentDurationFormatSetup from "moment-duration-format"

export const getDateDiffernece = (start, end) => {
  const endDate = moment(end);
  const startDate = moment(start);
  if (startDate > endDate)
    return 0
  momentDurationFormatSetup(moment);
  const minutesBetween = endDate.diff(startDate, "minutes");
  const duration = moment.duration(minutesBetween, "minutes").format();
  return duration;
};