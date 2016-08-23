/**
 * Created by charques on 23/8/16.
 */

var express = require('express');
var router = express.Router();
var auth = require(__base + 'app/middlewares/auth');

router.use('/authenticate', require('./authenticate'));
router.use('/users', auth, require('./users'));

router.get('/', function(req, res) {
    res.json({ message: 'Welcome to the /macrolayer/api/login' });
});

module.exports = router;