import { dateToConversationDisplay } from "../../helpers/time/dateUtils";

const HOUR = 3600000;

beforeAll(() => {
    Date.now = jest.fn(() => new Date("2020-09-28T23:00:00").valueOf());
});

test("dateToConvDisp: if given date < 1 day ago, return hour:minute PM/AM", function() {
    const dateToTest = new Date(Date.now());
    let dateAsString = dateToConversationDisplay(dateToTest);
    expect(dateAsString).toEqual("11:00 PM");
});

test("dateToConvDisp: if given date is yesterday, return 'Yesterday'", function() {
    const yesterday = new Date(Date.now() - 25 * HOUR);
    let dateAsString = dateToConversationDisplay(yesterday);
    expect(dateAsString).toEqual("Yesterday")
});

test("dateToConvDisp: if date is older than yesterday, return month/day/year", function() {
    const twoDaysAgo = new Date(Date.now() - 48 * HOUR);
    let dateAsString = dateToConversationDisplay(twoDaysAgo);
    expect(dateAsString).toEqual("09/26/20");
})