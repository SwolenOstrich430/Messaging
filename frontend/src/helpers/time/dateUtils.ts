import moment from "moment";

const YESTERDAY_DISPLAY = "Yesterday";


export const dateToConversationDisplay = (date: Date): string => {
    let hourDiff = moment().diff(date, "days");
    
    if(hourDiff < 1) {
        return moment(date).format("h:mm A");
    }

    if(hourDiff === 1) return YESTERDAY_DISPLAY;

    return moment(date).format("MM/DD/YY")
}