
const jwt = require('jsonwebtoken');

// 로그인 되어있다면 req.user에 유저정보 할당
const userConfig = async (req, res, next) => {
    try {
        const token = req.cookies.kodark
        if(!token) return next()
        const decodedToken = await jwt.verify(token, 'privateKey');
        req.user = decodedToken;
        return next();
    } catch(err) {
        console.log('userConfig error : ', err)
        next(err);
    }
}

module.exports = {
  userConfig
}
