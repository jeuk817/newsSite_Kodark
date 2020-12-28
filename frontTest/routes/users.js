var express = require('express');
var router = express.Router();

const { userConfig } = require('../middlewares/auth_middlware')

router.get('/', userConfig, (req, res, next) => {
  const user = req.user
  if(user) {
    // res.set('Links', '</users/sign-up>; rel="self", </ko/auth/signIn>; rel="next"')
    res.setHeader('Content-type', 'application/json')
    res.setHeader('Links', '</users/my-page>; rel="myPage", </users/my-page/detail>; rel="userDetail", </users/my-page/subscribed-list>; rel="subscribedList", </users/sign-out>; rel="signOut"')
    res.json({ email: user.email, auth: user.auth })
  } else {
    res.sendStatus(401)
  }
})

router.post('/sign-up', (req, res, next) => {
  const {email, pwd} = req.body
  console.log(email, pwd)
  if(email === 'asdf@asdf') {
    res.set('Links', '</users/sign-up>; rel="self", </ko/auth/signIn>; rel="next"')
    res.sendStatus(201)
  } else {
    res.sendStatus(409)
  }
})


module.exports = router;
