#!/bin/bash

# Testing with Apache Bench
# ---------------------------

# ---------------------------
# USER DETAIL
# ---------------------------

filename=log_user_detail-$(date "+%Y%m%d-%H:%M:%S").txt
echo ""
echo "### TESTING - User detail - 1000 request - 10 users >" "$filename"
echo ""

# User detail - 1000 request - 10 users
ab -n 1000 -c 10 http://localhost:2222/usuarios/123456789 > "$filename"
