
const Util = function() {}

// http response header에 links 헤더를 파싱하는 함수
// ex) '</ko/users/sign-up>; rel="self", </ko/signIn>; rel="next"'
Util.prototype.parseLinks = function(links){
  return links.split(', ').map(link => link.split('; ')).reduce((ac, cu) => {
    const [linkStr, relStr] = cu
    const link = linkStr.substring(1, linkStr.length -1) // remove '<' , '>'
    const rel = relStr.substring(5, relStr.length -1) // remove 'rel="', '"'
    ac[rel] = link
    return ac
  },{})
}

export default Util;
