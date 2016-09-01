/**
 * Created by charques on 23/8/16.
 */

'use strict';

var express = require('express');
var router  = express.Router();
var User    = require(__base + 'app/models/user');
var hash    = require(__base + 'app/helpers/crypto');

router.get('/', function(req, res) {
    User.find({}, function(err, users) {
        res.json(users);
    });
});

router.get('/:id', function(req, res) {
    User.findById(req.params.id, function (err, user) {
        if (err) {
            throw err;
        }

        res.json(user);
    });
});

router.post('/', function(req, res) {

    // TODO validate input

    // create a sample user
    var user = new User({
        email: req.body.email,
        password: hash(req.body.password, req.app.get('secret-key')),
        admin: req.body.admin
    });
    //var error = user.validateSync();

    // save the sample user
    user.save(function(err) {
        if (err) {
            throw err;
        }

        console.log('User saved successfully');
        res.json({ success: true });
    });

});

module.exports = router;
