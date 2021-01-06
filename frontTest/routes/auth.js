const express = require('express')
const router = express.Router()
const jwt = require('jsonwebtoken')

const { userConfig } = require('../middlewares/auth_middlware')

router.post('/', (req, res, next) => {
  const {email} = req.body
  console.log(email)
  if(email === 'jeuk817@gmail.com') {
    res.sendStatus(409) // conflict
  } else {
    res.sendStatus(201)
  }
})

router.patch('/verify', (req, res, next) => {
  const { email, authString } = req.body
  if(authString === '123123') {
    res.sendStatus(204) // success
  } else if(authString === '111111') {
    res.sendStatus(401) // wrong
  } else {
    res.sendStatus(408) // time out
  }
})

router.post('/sign-in', (req, res, next) => {
  const { email, pwd } = req.body
  if(email === 'jeuk817@gmail.com' && pwd === '!234qwer') {
      const token = jwt.sign({
        email: 'jeuk817@gmail.com',
        id: '1',
        auth: 'user'
      }, 'privateKey', {
        expiresIn: 60 * 60* 60 * 60,
        issuer: 'jack'
      });

      res.cookie('kodark', token, {
        httpOnly: true,
        maxAge: 60 * 60* 60 * 60
      })

      res.set('Links', '</auth/sign-in>; rel="self", </ko/home>; rel="next"')
      // res.json('s')
      res.sendStatus(204) // sign in
  } else {
    res.sendStatus(401) // unauthorized
  }
})

router.delete('/sign-out', userConfig, (req, res, next) => {
  if(req.user) {
    res.clearCookie('kodark', { path: '/' });
    res.sendStatus(205)
  } else {
    res.sendStatus(401)
  }
})

router.get('/idCheck', (req, res, next) => { // /auth/idCheck
  // res.json(data)
  console.log('/idCheck')
  res.set('Links', '<http://localhost:3000/signIn>; rel="next"')
  res.send('hi!')
  // console.log(req.is('*/*'))
  // console.log('------------------------------')
  // console.log('req.headers : ',req.headers)
  // console.log('------------------------------')
  // console.log('req.header() : ', req.header())
  // console.log('------------------------------')
  // var contype = req.headers['content-type'];
  // console.log('contype : ', contype)
  // res.status(500)
  // res.send('error 500!')
})

router.get('/paramTest', (req, res, next) => {
  console.log('-----')
  console.log(req.query.aa)
  console.log(req.query.bb)
  res.send('dd')
})

router.post('/paramTest', (req, res, next)=> {
  console.log(req.body)
  res.send('success')
})

module.exports = router;
