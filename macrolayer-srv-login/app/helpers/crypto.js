/**
 * Created by charques on 23/8/16.
 */
var crypto  = require('crypto');

function hash(password, secret) {
    return crypto.createHmac('sha256', secret)
        .update(password)
        .digest('hex');
}

module.exports = hash;