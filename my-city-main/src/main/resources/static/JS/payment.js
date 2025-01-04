{/* <script src="https://checkout.razorpay.com/v1/checkout.js"></script> */}

    function sendOrderDetailsToBackend(paymentDetails) {
        // Make sure the required fields are correctly passed
        const paymentDetailsToSend = {
            name: paymentDetails.name,
            email: paymentDetails.email,
            amount: paymentDetails.amount,
            description: paymentDetails.description,
            paymentStatus: paymentDetails.paymentStatus,  // Should be 'Success' or 'Failed'
            paymentId: paymentDetails.paymentId,  // This comes from Razorpay
        };
    }
    
    // Razorpay payment options
    document.getElementById('payment-button').addEventListener('click', function() {
        var name = document.getElementById('name').value;
        var email = document.getElementById('email').value;
        var amount = parseFloat(document.getElementById('amount').value);  // Ensure amount is a number
        var description = document.getElementById('description').value;
    
        // Validation for empty fields
        if (!name || !email || isNaN(amount) || !description) {
            alert('Please fill all the fields');
            return;
        }
    
        // Razorpay payment options
        var options = {
            key: "rzp_test_hr66RzeUcyoNO7",  // Replace with your Razorpay key
            amount: amount * 100,  // Amount in paise (1 INR = 100 paise)
            currency: "INR",
            name: name,
            description: description,
            handler: function(response) {
                // After payment is successful, process the response
                alert("Payment Successful!");
                console.log(response);
    
                // Prepare payment details to send to the backend
                var paymentDetails = {
                    name: name,
                    email: email,
                    amount: amount,
                    description: description,
                    paymentStatus: 'Success',  // Since payment was successful
                    paymentId: response.razorpay_payment_id,  // Razorpay payment ID
                };
    
                // Send the payment details to the backend (MongoDB)
                sendOrderDetailsToBackend(paymentDetails);
            },
            prefill: {
                name: name,
                email: email
            },
            theme: {
                color: "#4CAF50"
            }
        };
    
        var rzp1 = new Razorpay(options);
        rzp1.open();
    });
