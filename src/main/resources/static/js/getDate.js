const monthsList = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
];

const daysList = [
    'Sunday',
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday'
];

function getDate(value) {

    const date = new Date(value);

    let year = date.getFullYear();
    let month = monthsList[date.getMonth()];
    let dayMonth = daysList[date.getDay()];
    let day = date.getDate();

    let fullDate = `${dayMonth} ${month} ${day}, ${year}`;

    return fullDate; // I.E. Wednesday June 9th, 2021
}