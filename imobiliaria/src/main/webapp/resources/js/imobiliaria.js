$(function() {
	$('.js-moeda').maskMoney({ decimal: ',', thousands: '.' });
	$('[data-toggle="popover"]').popover({container: "body"});
});