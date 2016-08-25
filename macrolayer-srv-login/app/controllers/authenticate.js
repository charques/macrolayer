/**
 * Created by charques on 23/8/16.
 */

'use strict';

var express = require('express');
var jwt     = require('jsonwebtoken'); // used to create, sign, and verify tokens
var router  = express.Router();
var User    = require(__base + 'app/models/user');
var hash    = require(__base + 'app/helpers/crypto');

router.post('/', function(req, res) {

    // find the user
    User.findOne({
        name: req.body.name
    }, function(err, user) {

        if (err) {
            throw err;
        }

        if (!user) {
            res.status(401);
            res.json({ success: false, message: 'Authentication failed. User not found.' });
        } else if (user) {

            // check if password matches
            var encryptedPassword = hash(req.body.password, req.app.get('secret-key'));
            console.log(user.password);
            console.log(encryptedPassword);
            if (user.password !== encryptedPassword) {
                res.status(401);
                res.json({ success: false, message: 'Authentication failed. Wrong password.' });
            } else {

                // if user is found and password is right
                // create a token
                var token = jwt.sign(user, req.app.get('secret-key'), {
                    expiresIn: 60*60*24 // expires in 24 hours
                });

                // return the information including token as JSON
                res.json({
                    success: true,
                    message: 'Enjoy your token2!',
                    token: token
                });
            }

        }

    });
});

module.exports = router;