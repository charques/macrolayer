/**
 * Created by charques on 23/8/16.
 */

'use strict';

var config = require(__base + 'app/config/config');
var User    = require(__base + 'app/models/user');
var hash    = require(__base + 'app/helpers/crypto');

var createDefaultUser = function() {

    User.find({ 'email': 'admin@macrolayer.io' }, function (err, users) {
        if (err) {
            throw err;
        }

        if (users.length === 0) {
            // create a default user
            var user = new User({
                email: 'admin@macrolayer.io',
                password: hash('admin123', config.secret),
                admin: true
            });

            // save the default user
            user.save(function(err) {
                if (err) {
                    throw err;
                }

                console.log('Default user saved successfully');
            });
        }
        else {
            console.log('Default user already exists');
        }
    });
};

createDefaultUser();
